package studio.yttrium.core;

import studio.yttrium.pojo.Column;
import studio.yttrium.pojo.CommonExample;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/17
 * Time: 15:34
 */
public interface CommonOperation {

    /**
     * 查询所有数据库名字列表
     *
     * @param jdbcOperation 已经生成的操作类
     * @return List<String>
     * @throws SQLException SQL异常
     */
    List<?> queryDataNameList(JdbcOperation jdbcOperation) throws SQLException;

    /**
     * 查询所有的表名 根据数据库名
     *
     * @param jdbcOperation 已经生成的操作类
     * @param databaseName  数据库名
     * @return List<String>
     * @throws SQLException SQL异常
     */
    List<?> queryTableNameList(JdbcOperation jdbcOperation, String databaseName) throws SQLException;

    /**
     * 查询数据
     *
     * @param jdbcOperation 已经生成的操作类
     * @param example       CommonExample
     * @return List<Map>
     * @throws SQLException SQL异常
     */
    List<Map<String, Object>> queryTableList(JdbcOperation jdbcOperation, CommonExample example) throws SQLException;


    /**
     * 查询数据
     *
     * @param jdbcOperation 已经生成的操作类
     * @param sql           sql语句
     * @return List<Map>
     * @throws SQLException SQL异常
     */
    List<Map<String, Object>> queryTableList(JdbcOperation jdbcOperation, String sql) throws SQLException;

    /**
     * 查询数据条数
     *
     * @param jdbcOperation 已经生成的操作类
     * @param example       CommonExample
     * @return long
     * @throws SQLException SQL异常
     */
    long queryTableCount(JdbcOperation jdbcOperation, CommonExample example) throws SQLException;


    /**
     * 查询 字段详细信息
     *
     * @param jdbcOperation 已经生成的操作类
     * @param example       CommonExample
     * @return List<Column>
     * @throws SQLException SQL异常
     */
    List<Column> queryTableColumnList(JdbcOperation jdbcOperation, CommonExample example) throws SQLException;

    /**
     * 添加一条记录
     *
     * @param jdbcOperation 已经生成的操作类
     * @param tableName     表名
     * @param params        添加的字段
     * @return 成功条数
     */
    public int addRecord(JdbcOperation jdbcOperation, String tableName, Map<String, Object> params) throws SQLException;
}
