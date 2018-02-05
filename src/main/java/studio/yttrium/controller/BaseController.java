package studio.yttrium.controller;

import studio.yttrium.core.CommonOperation;
import studio.yttrium.core.JdbcOperation;
import studio.yttrium.pojo.DatabaseInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/12/6
 * Time: 21:32
 */
public class BaseController {

    @Resource
    CommonOperation commonOperation;

    static String SESSION_JDBC_KEY = "jdbcOperation";
    static String SESSION_INFO_KEY = "loginInfo";

    String getParameterString(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    int getParameterInt(HttpServletRequest request, String key) {
        try {
            return Integer.parseInt(request.getParameter(key));
        } catch (Exception e) {
            return 0;
        }
    }

    JdbcOperation getJdbcOperation(HttpServletRequest request) {
        return (JdbcOperation) request.getSession().getAttribute(SESSION_JDBC_KEY);
    }

    DatabaseInfo getDatabaseInfo(HttpServletRequest request) {
        return (DatabaseInfo) request.getSession().getAttribute(SESSION_INFO_KEY);
    }
}
