package studio.yttrium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import studio.yttrium.core.JdbcOperation;
import studio.yttrium.core.XmlOperation;
import studio.yttrium.pojo.DatabaseInfo;
import studio.yttrium.pojo.SqlRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/11/21
 * Time: 18:59
 */
@Controller
public class RouterController extends BaseController {

    @Resource
    private XmlOperation xmlOperation;

    /**
     * 登录的路由
     *
     * @return other
     */
    @RequestMapping("login")
    public String register() {
        return "login";
    }

    /**
     * 欢迎界面的路由
     * @return
     */
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }
    /**
     * 退出登录
     *
     * @param session Session
     * @return view
     */
    @RequestMapping("loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute(SESSION_JDBC_KEY);
        session.removeAttribute(SESSION_INFO_KEY);
        return "login";
    }

    /**
     * sql转跳的路由
     *
     * @return view
     */
    @RequestMapping("sql")
    public String sql() {
        return "sql";
    }

    /**
     * 主页 Home
     *
     * @param request HttpServletRequest
     * @param view    Model
     * @return 页面
     * @throws SQLException SQL错误
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request, Model view) throws SQLException {
        JdbcOperation jdbcOperation = getJdbcOperation(request);
        DatabaseInfo info = getDatabaseInfo(request);
        if (null != jdbcOperation && null != info && jdbcOperation.checkConnection()) {
            List<SqlRecord> sqlRecordList = xmlOperation.querySqlRecordList(info.getSQLPrimary());
            view.addAttribute("dataNameList", commonOperation.queryDataNameList(jdbcOperation));
            if (sqlRecordList != null && sqlRecordList.size() > 0) view.addAttribute("SqlRecordList", sqlRecordList);
            view.addAttribute("defaultDataName", info.getDatabaseName());
            view.addAttribute("tableNameList", commonOperation.queryTableNameList(jdbcOperation, info.getDatabaseName()));
            return "index";
        }
        //去登录界面
        return "login";
    }
}
