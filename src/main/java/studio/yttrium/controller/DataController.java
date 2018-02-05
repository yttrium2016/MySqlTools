package studio.yttrium.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.yttrium.core.JdbcOperation;
import studio.yttrium.pojo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/18
 * Time: 13:16
 */
@Controller
@RequestMapping("/api")
public class DataController extends BaseController {

    @RequestMapping("/getData")
    @ResponseBody
    public TableDataResult getData(HttpServletRequest request) {
        TableDataResult result = new TableDataResult();
        try {
            //接受参数
            int draw = getParameterInt(request, "draw");
            String tableName = getParameterString(request, "tableName");
            int pageIndex = getParameterInt(request, "start");
            int pageSize = getParameterInt(request, "length");
            String searchValue = getParameterString(request, "search[value]");

            JdbcOperation jdbcOperation = getJdbcOperation(request);
            DatabaseInfo info = getDatabaseInfo(request);
            if (null != jdbcOperation && null != info && jdbcOperation.checkConnection()) {
                //拼装搜索条件 带过滤功能
                CommonExample example = new CommonExample(tableName);
                List<Column> columnList = commonOperation.queryTableColumnList(jdbcOperation, example);
                for (Column c : columnList) {
                    //先统一时间类型这样以后慢慢改
                    if ("Date".equals(c.getType())) {
                        example.addColumnName("date_format(" + c.getSelectColumn() + ", '%Y-%c-%d %h:%i:%s') AS " + c.getSelectColumn());
                    } else {
                        example.addColumnName(c.getSelectColumn());
                    }
                    //如果搜索信息不为空 字符类型的
                    if ("String".equals(c.getType())) {
                        if (StringUtils.isNotBlank(searchValue)) {
                            CommonExample.Criteria criteria = example.or();
                            criteria.andColumnLike(c.getSelectColumn(), "%" + searchValue + "%");
                        }
                    }
                }

                if (pageSize != -1) {
                    example.setLimitParam(pageIndex, pageSize);
                }
                //查询结果
                List<Map<String, Object>> data = commonOperation.queryTableList(jdbcOperation, example);
                result.setData(data == null ? new ArrayList<>() : data);
                //请求次数
                result.setDraw(draw);
                //查询结果总数
                long size = commonOperation.queryTableCount(jdbcOperation, example);
                result.setRecordsFiltered(size);
                result.setRecordsTotal(size);
                return result;
            } else {
                System.out.println("没有登录...或者登录信息已失效...请重新登录");
                throw new RuntimeException("没有登录...");
            }
        } catch (Exception e) {
            System.out.println("[查询出错]:" + e.getMessage());
            return null;
        }
    }

    @RequestMapping("/executeSQL")
    @ResponseBody
    public AjaxResult executeSQL(HttpServletRequest request, String sql) {
        JdbcOperation jdbcOperation = getJdbcOperation(request);
        DatabaseInfo info = getDatabaseInfo(request);
        sql = sql.trim();
        if (StringUtils.isBlank(sql)) {
            return AjaxResult.error("查询的sql语句不能为空");
        }
        if (sql.toLowerCase().contains("insert")) {
            return AjaxResult.error("查询的SQL语句不能包含INSERT");
        }
        if (sql.toLowerCase().contains("update")) {
            return AjaxResult.error("查询的SQL语句不能包含UPDATE");
        }
        if (!sql.toLowerCase().contains("limit")) {
            if (sql.charAt(sql.length() - 1) == ';') {
                sql = sql.substring(0, sql.length() - 1) + " limit 0, 500 ;";
            } else {
                sql = sql + " limit 0, 500 ;";
            }
        }
        if (null != jdbcOperation && null != info && jdbcOperation.checkConnection()) {
            try {
                List<Map<String, Object>> data = commonOperation.queryTableList(jdbcOperation, sql);
                return AjaxResult.success("查询成功").data(data);
            } catch (Exception e) {
                System.out.println("executeSQL语句有问题:" + e.getMessage());
                return AjaxResult.error("SQL语句有问题:[" + e.getMessage() + "]");
            }
        } else {
            return AjaxResult.error("没有登录...或者登录信息已失效...请重新登录");
        }
    }

}
