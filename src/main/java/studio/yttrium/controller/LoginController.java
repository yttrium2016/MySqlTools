package studio.yttrium.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.yttrium.core.JdbcOperation;
import studio.yttrium.core.RowMapper;
import studio.yttrium.core.XmlOperation;
import studio.yttrium.core.impl.SimpleJdbcOperationImpl;
import studio.yttrium.pojo.AjaxResult;
import studio.yttrium.pojo.DatabaseInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/12/4
 * Time: 16:44
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {


    @Resource
    private XmlOperation xmlOperation;

    /**
     * 查找所有数据库的名字
     *
     * @param info 数据库连接信息封装对象
     * @return AjaxResult
     */
    @RequestMapping("/searchDatabaseNames")
    @ResponseBody
    public AjaxResult searchDatabaseNames(DatabaseInfo info) {
        if (null != info && info.isSearchDatabaseName()) {
            try {
                JdbcOperation jdbcOperation = new SimpleJdbcOperationImpl(info.getBaseDatabaseInfo());
                if (jdbcOperation.checkConnection()) {
                    String sql = "SELECT `SCHEMA_NAME` AS databaseName FROM `information_schema`.`SCHEMATA` WHERE `SCHEMA_NAME` NOT IN ('information_schema','performance_schema','mysql') ;";
                    List<?> list = jdbcOperation.queryForBean(sql, (RowMapper<String>) rs -> rs.getString(1));
                    if (list != null && list.size() > 0) {
                        return AjaxResult.success("查询成功").data(list);
                    } else {
                        return AjaxResult.error("连接数据库成功,数据库返回为空..");
                    }
                } else {
                    return AjaxResult.error("连接数据库失败,请重新填写信息..");
                }
            } catch (Exception e) {
                return AjaxResult.serverError(e.getMessage());
            }
        } else {
            return AjaxResult.error("请先填写信息进行数据库名查询..");
        }
    }

    /**
     * 登录操作
     *
     * @param info 登录信息
     * @return AjaxResult
     */
    @RequestMapping("/submit")
    @ResponseBody
    public AjaxResult submit(DatabaseInfo info, HttpSession session) {
        if (info != null && info.isSearchDatabaseName()) {
            try {
                JdbcOperation jdbcOperation = new SimpleJdbcOperationImpl(info);
                if (jdbcOperation.checkConnection()) {
                    session.setAttribute(SESSION_INFO_KEY, info);
                    session.setAttribute(SESSION_JDBC_KEY, jdbcOperation);
                    xmlOperation.addAndEditDatabaseInfo(info);
                    return AjaxResult.success("登录成功").url("/index.shtml");
                } else {
                    return AjaxResult.error("连接数据库失败,请重新填写信息..");
                }
            } catch (Exception e) {
                return AjaxResult.serverError(e.getMessage());
            }
        } else {
            return AjaxResult.error("请先填写信息进行数据库名查询..");
        }
    }

    /**
     * 修改数据库
     *
     * @param dataName 修改的数据库名
     * @return AjaxResult
     */
    @RequestMapping("/updateDataName")
    @ResponseBody
    public AjaxResult updateDataName(String dataName, HttpServletRequest request) {
        if (StringUtils.isNotBlank(dataName)) {
            JdbcOperation jdbcOperation = getJdbcOperation(request);
            DatabaseInfo info = getDatabaseInfo(request);
            if (jdbcOperation.initConnection(info.databaseName(dataName))) {
                HttpSession session = request.getSession();
                session.setAttribute(SESSION_INFO_KEY, info);
                session.setAttribute(SESSION_JDBC_KEY, jdbcOperation);
                return AjaxResult.success("修改成功");
            }
        }
        return AjaxResult.error("修改失败");
    }
}
