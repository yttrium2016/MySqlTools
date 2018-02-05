package studio.yttrium.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.yttrium.core.JdbcOperation;
import studio.yttrium.core.XmlOperation;
import studio.yttrium.helper.ExportEXCELHelper;
import studio.yttrium.pojo.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/2/2
 * Time: 17:05
 */
@Controller
public class SqlController extends BaseController {

    @Resource
    private XmlOperation xmlOperation;

    @RequestMapping("/sql/save")
    @ResponseBody
    public AjaxResult save(HttpServletRequest request) {
        try {
            DatabaseInfo info = getDatabaseInfo(request);
            String sql = getParameterString(request, "sql");
            String name = getParameterString(request, "name");
            String recordId = getParameterString(request, "recordId");
            SqlRecord record = new SqlRecord();
            if (StringUtils.isNotBlank(recordId)) {
                record.setId(recordId);
            } else {
                String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
                record.setId(id);
            }
            record.setName(name);
            record.setSql(sql);
            record.setDatabasePrimary(info.getSQLPrimary());
            xmlOperation.addAndEditSqlRecord(info.getSQLPrimary(), record);
            return AjaxResult.success("保存成功");
        } catch (Exception e) {
            return AjaxResult.serverError("[保存出错]" + e.getMessage());
        }
    }

    @RequestMapping("/sql-{id}")
    public String getData(@PathVariable(name = "id") String id, HttpServletRequest request, Model view) {
        try {
            DatabaseInfo info = getDatabaseInfo(request);
            SqlRecord record = xmlOperation.querySqlRecord(info.getSQLPrimary(), id);
            view.addAttribute("record", record);
            return "sql";
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/sql/output-{id}")
    @ResponseBody
    public AjaxResult outPutById(@PathVariable(name = "id") String id, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        JdbcOperation jdbcOperation = getJdbcOperation(request);
        DatabaseInfo info = getDatabaseInfo(request);
        ServletOutputStream os = null;
        try {
            if (null != jdbcOperation && null != info && jdbcOperation.checkConnection()) {
                List<Map<String, Object>> data;
                if ("sql".equals(id)) {
                    String sql = getParameterString(request, "sql").trim();
                    if (StringUtils.isBlank(sql)) {
                        return AjaxResult.error("查询的sql语句不能为空");
                    }
                    if (sql.toLowerCase().contains("insert")) {
                        return AjaxResult.error("查询的SQL语句不能包含INSERT");
                    }
                    if (sql.toLowerCase().contains("update")) {
                        return AjaxResult.error("查询的SQL语句不能包含UPDATE");
                    }
                    data = commonOperation.queryTableList(jdbcOperation, sql);
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("表格.xls", "utf-8"));
                    os = response.getOutputStream();
                } else {
                    SqlRecord record = xmlOperation.querySqlRecord(info.getSQLPrimary(), id);
                    if (null == record) {
                        return AjaxResult.error("没有找到对应的记录..");
                    }
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(record.getName() + "-表格.xls", "utf-8"));
                    os = response.getOutputStream();
                    data = commonOperation.queryTableList(jdbcOperation, record.getSql());
                }
                if (data != null && data.size() > 0) {
                    Map<String, Object> map = data.get(0);
                    if (map != null && map.size() > 0) {
                        String[] title = new String[map.size()];
                        int i = 0;
                        for (String key : map.keySet()) {
                            title[i] = key;
                            i++;
                        }
                        HSSFWorkbook workbook = ExportEXCELHelper.exportExcel("数据表", title, title, data);
                        workbook.write(os);
                        return null;
                    } else {
                        return AjaxResult.error("查询数据中字段为空..");
                    }
                } else {
                    return AjaxResult.error("查询数据为空..");
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
