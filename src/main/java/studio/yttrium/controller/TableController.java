package studio.yttrium.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.yttrium.core.JdbcOperation;
import studio.yttrium.helper.ExportEXCELHelper;
import studio.yttrium.pojo.AjaxResult;
import studio.yttrium.pojo.Column;
import studio.yttrium.pojo.CommonExample;
import studio.yttrium.pojo.DatabaseInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/12/13
 * Time: 17:15
 */
@Controller
public class TableController extends BaseController {

    @RequestMapping("/table-{tableName}")
    public String table(@PathVariable(name = "tableName") String tableName, HttpServletRequest request, Model view) throws SQLException {
        view.addAttribute("tableName", tableName);
        JdbcOperation jdbcOperation = getJdbcOperation(request);
        DatabaseInfo info = getDatabaseInfo(request);
        if (null != jdbcOperation && null != info && jdbcOperation.checkConnection()) {
            List<Column> list = commonOperation.queryTableColumnList(jdbcOperation, new CommonExample(tableName, 0, 1));
            view.addAttribute("columnList", list);
            return "table";
        } else {
            //去登录页面
            return "login";
        }
    }


    @RequestMapping("/add-{tableName}")
    @ResponseBody
    public AjaxResult addItem(@PathVariable(name = "tableName") String tableName, @RequestParam Map<String, String> params, HttpServletRequest request) throws SQLException {

        try {
            Map<String, Object> data = new HashMap<>();
            JdbcOperation jdbcOperation = getJdbcOperation(request);
            List<Column> list = commonOperation.queryTableColumnList(jdbcOperation, new CommonExample(tableName, 0, 1));
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (StringUtils.isNotBlank(value)) {
                    for (Column c : list) {
                        if (key.equals(c.getColumnName())) {
                            switch (c.getType()) {
                                case "Boolean":
                                    data.put(key, Boolean.valueOf(value));
                                    break;
                                case "Int":
                                    data.put(key, Integer.valueOf(value));
                                    break;
                                case "Long":
                                    data.put(key, Long.valueOf(value));
                                    break;
                                case "Double":
                                    data.put(key, Double.valueOf(value));
                                    break;
                                case "Byte":
                                    data.put(key, Byte.valueOf(value));
                                    break;
                                case "Date":
                                    data.put(key, Date.valueOf(value));
                                    break;
                                default:
                                    data.put(key, value);
                                    break;
                            }
                            break;
                        }
                    }
                }
            }
            int res = commonOperation.addRecord(jdbcOperation, tableName, data);
            if (res > 0) {
                return AjaxResult.success("添加成功");
            } else {
                return AjaxResult.error("添加失败");
            }
        } catch (Exception e) {
            return AjaxResult.error("[添加出错]:" + e.getMessage());
        }
    }

    @RequestMapping("/output-{tableName}")
    @ResponseBody
    public AjaxResult outPut(@PathVariable(name = "tableName") String tableName, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        JdbcOperation jdbcOperation = getJdbcOperation(request);
        DatabaseInfo info = getDatabaseInfo(request);
        ServletOutputStream os = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(tableName + "-表格.xls", "utf-8"));
            os = response.getOutputStream();
            if (null != jdbcOperation && null != info && jdbcOperation.checkConnection()) {
                //拼装搜索条件 带过滤功能
                CommonExample example = new CommonExample(tableName);
                List<Column> columnList = commonOperation.queryTableColumnList(jdbcOperation, example);
                String[] title = null;
                if (columnList != null && columnList.size() > 0) {
                    title = new String[columnList.size()];
                    int i = 0;
                    for (Column c : columnList) {
                        //先统一时间类型这样以后慢慢改
                        if ("Date".equals(c.getType())) {
                            example.addColumnName("date_format(" + c.getSelectColumn() + ", '%Y-%c-%d %h:%i:%s') AS " + c.getSelectColumn());
                        } else {
                            example.addColumnName(c.getSelectColumn());
                        }
                        title[i] = c.getColumnName();
                        i++;
                    }
                }
                example.setLimit(false);
                //查询结果
                List<Map<String, Object>> data = commonOperation.queryTableList(jdbcOperation, example);
                if (data != null && data.size() > 0) {
                    HSSFWorkbook workbook = ExportEXCELHelper.exportExcel("数据表", title, title, data);
                    workbook.write(os);
                    return null;
                } else {
                    return AjaxResult.error("数据为空..");
                }
            } else {
                return AjaxResult.error("没有登录...或者登录信息已失效...请重新登录");
            }
        } catch (Exception e) {
            return AjaxResult.serverError(e.getMessage());
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }
}
