package studio.yttrium.pojo;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/12/25
 * Time: 13:57
 */
public class AjaxResult extends HashMap<String, Object> {

    /**
     * 成功
     */
    private static int SUCCESS = 0;
    /**
     * 失败
     */
    private static int ERROR = -1;
    /**
     * 服务器错误
     */
    private static int SERVER_ERROR = 500;

    public AjaxResult() {
        put("code", SUCCESS);
    }

    public static AjaxResult error() {
        return error(ERROR, "发生错误,请打110报警");
    }

    public static AjaxResult error(String msg) {
        return error(ERROR, msg);
    }

    public static AjaxResult error(int code, String msg) {
        AjaxResult r = new AjaxResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static AjaxResult serverError() {
        return error(SERVER_ERROR, "发生系统错误,请打110报警");
    }

    public static AjaxResult serverError(String msg) {
        return error(SERVER_ERROR, msg);
    }

    public static AjaxResult success(String msg) {
        AjaxResult r = new AjaxResult();
        r.put("msg", msg);
        return r;
    }

    public static AjaxResult success() {
        return new AjaxResult();
    }

    public AjaxResult data(Object value) {
        super.put("data", value);
        return this;
    }

    public AjaxResult url(String url) {
        super.put("url", url);
        return this;
    }
}
