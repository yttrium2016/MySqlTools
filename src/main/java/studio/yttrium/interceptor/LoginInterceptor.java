package studio.yttrium.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import studio.yttrium.core.JdbcOperation;
import studio.yttrium.pojo.DatabaseInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/12/13
 * Time: 16:17
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从传入的handler中检查是否有AuthCheck的声明
        HandlerMethod method = (HandlerMethod) handler;
        String requestURI = request.getRequestURI();
        //|| requestURI.equals("/") || requestURI.contains(".json") || requestURI.contains(".do") || requestURI.contains(".action")
        if (StringUtils.isNotBlank(requestURI) && requestURI.length() > 5 && "/login".equals(requestURI.substring(0, 6)))
            return true;
        //获得session
        HttpSession session = request.getSession();
        JdbcOperation jdbcOperation = (JdbcOperation) session.getAttribute("jdbcOperation");
        DatabaseInfo info = (DatabaseInfo) session.getAttribute("loginInfo");
        if (jdbcOperation == null || info == null || !jdbcOperation.checkConnection()) {
            response.sendRedirect("/login.shtml");
            return false;
        }
        //检查通过，返回true，方法会继续执行
        return true;
    }
}
