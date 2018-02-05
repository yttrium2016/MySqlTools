package studio.yttrium.core.impl;


import studio.yttrium.core.JdbcOperation;
import studio.yttrium.core.RowMapper;
import studio.yttrium.pojo.Column;
import studio.yttrium.pojo.DatabaseInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/8
 * Time: 11:15
 */
public class SimpleJdbcOperationImpl implements JdbcOperation {

    private static final boolean AUTO_COMMIT = true;

    private static String driverClass = "com.mysql.cj.jdbc.Driver";

    private DatabaseInfo info;

    static {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SimpleJdbcOperationImpl() {
    }

    public SimpleJdbcOperationImpl(DatabaseInfo info) {
        this.info = info;
    }

    public Connection getConnection() {
        return getConnection(AUTO_COMMIT);
    }

    public Connection getConnection(boolean autoCommit) {
        try {
            Connection conn = DriverManager.getConnection(info.getJdbcUrl(), info.getUsername(), info.getPassword());
            if (!autoCommit)
                conn.setAutoCommit(autoCommit);
            return conn;
        } catch (SQLException e) {
            System.out.println("获取连接失败:" + e.getMessage());
        }
        return null;
    }

    @Override
    public int execute(String sql, Object[] params) throws SQLException {
        Connection conn = getConnection(false);
        PreparedStatement stmt = null;
        int result = -1;
        try {
            stmt = createPreparedStatement(conn, sql, params);
            result = stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            close(stmt);
            close(conn);
        }
        return result;
    }

    @Override
    public int execute(String sql) throws SQLException {
        return execute(sql, new Object[]{});
    }

    @Override
    public ResultSet queryForResultSet(String sql, Object[] params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = createPreparedStatement(conn, sql, params);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw e;
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public ResultSet queryForResultSet(String sql) throws SQLException {
        return queryForResultSet(sql, new Object[]{});
    }

    @Override
    public int queryForInt(String sql, Object[] params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = createPreparedStatement(conn, sql, params);
            rs = createResultSet(stmt);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
        return 0;
    }

    @Override
    public int queryForInt(String sql) throws SQLException {
        return queryForInt(sql, new Object[]{});
    }

    @Override
    public long queryForLong(String sql, Object[] params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = createPreparedStatement(conn, sql, params);
            rs = createResultSet(stmt);
            while (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
        return 0;
    }

    @Override
    public long queryForLong(String sql) throws SQLException {
        return queryForLong(sql, new Object[]{});
    }

    @Override
    public List<?> queryForBean(String sql, Object[] params, RowMapper<?> mapper) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> list = null;
        try {
            stmt = createPreparedStatement(conn, sql, params);
            rs = createResultSet(stmt);
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapper.parseRow(rs));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
        return list;
    }

    @Override
    public List<?> queryForBean(String sql, RowMapper<?> mapper) throws SQLException {
        return queryForBean(sql, new Object[]{}, mapper);
    }

    @Override
    public List<Map<String, Object>> queryForMap(String sql, Object[] params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = createPreparedStatement(conn, sql, params);
            rs = createResultSet(stmt);

            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map;
            ResultSetMetaData rsd = rs.getMetaData();
            int columnCount = rsd.getColumnCount();

            while (rs.next()) {
                map = new LinkedHashMap<>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }

            return list;
        } catch (SQLException e) {
            throw e;
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
    }

    @Override
    public List<Map<String, Object>> queryForMap(String sql) throws SQLException {
        return queryForMap(sql, new Object[]{});
    }

    @Override
    public int executeBatch(String sql, List<Object[]> params) throws SQLException {
        int result = 0;
        Connection conn = getConnection(false);
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                Object[] param = params.get(i);
                for (int j = 0; j < param.length; j++)
                    stmt.setObject(j + 1, param[j]);
                stmt.addBatch();
                if (i % 1000 == 0) {
                    stmt.executeBatch();
                    stmt.clearBatch();
                }
            }
            stmt.executeBatch();
            conn.commit();
            result = params.size();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            close(stmt);
            close(conn);
        }
        return result;
    }

    @Override
    public int executeBatch(String sql) throws SQLException {
        return executeBatch(sql, new ArrayList<>());
    }

    @Override
    public void close(Connection x) {
        if (x != null)
            try {
                x.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void close(Statement x) {
        if (x != null)
            try {
                x.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void close(PreparedStatement x) {
        if (x != null)
            try {
                x.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void close(ResultSet x) {
        if (x != null)
            try {
                x.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public PreparedStatement createPreparedStatement(Connection conn, String sql, Object[] params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++)
            stmt.setObject(i + 1, params[i]);
        return stmt;
    }

    public ResultSet createResultSet(PreparedStatement stmt) throws SQLException {
        return stmt.executeQuery();
    }


    @Override
    public List<Column> queryForColumn(String sql, Object[] params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = createPreparedStatement(conn, sql, params);
            rs = createResultSet(stmt);

            List<Column> result = new ArrayList<>();
            ResultSetMetaData rsd = rs.getMetaData();
            int columnCount = rsd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                Column column = new Column();
                column.setAutoIncrement(rsd.isAutoIncrement(i));
                column.setKey(rsd.getColumnName(i));
                column.setColumnSize(rsd.getColumnDisplaySize(i));
                column.setIsNullable(rsd.isNullable(i));
                column.setTableName(rsd.getTableName(i));
                column.setColumnLabel(rsd.getColumnLabel(i));
                column.setColumnName(rsd.getColumnName(i));
                column.setColumnType(rsd.getColumnType(i));
                column.setColumnTypeName(rsd.getColumnTypeName(i));
                result.add(column);
            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
    }

    @Override
    public List<Column> queryForColumn(String sql) throws SQLException {
        return queryForColumn(sql, new Object[]{});
    }

    @Override
    public boolean checkConnection() {
        Connection conn = getConnection();
        if (conn != null) {
            close(conn);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean initConnection(DatabaseInfo info) {
        this.info = info;
        return checkConnection();
    }
}
