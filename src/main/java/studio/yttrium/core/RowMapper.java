package studio.yttrium.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 返回泛型数据的接口
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/8
 * Time: 11:14
 */
public interface RowMapper<T> {

    public abstract T parseRow(ResultSet rs) throws SQLException;
}
