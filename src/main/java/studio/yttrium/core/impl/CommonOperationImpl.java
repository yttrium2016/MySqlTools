package studio.yttrium.core.impl;

import org.springframework.stereotype.Component;
import studio.yttrium.core.CommonOperation;
import studio.yttrium.core.JdbcOperation;
import studio.yttrium.core.RowMapper;
import studio.yttrium.pojo.Column;
import studio.yttrium.pojo.CommonExample;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/17
 * Time: 15:34
 */
@Component
public class CommonOperationImpl implements CommonOperation {

    @Override
    public List<?> queryDataNameList(JdbcOperation jdbcOperation) throws SQLException {
        String sql = "SELECT `SCHEMA_NAME` AS databaseName FROM `information_schema`.`SCHEMATA` WHERE `SCHEMA_NAME` NOT IN ('information_schema','performance_schema','mysql') ;";
        return jdbcOperation.queryForBean(sql, (RowMapper<String>) rs -> rs.getString(1));
    }

    @Override
    public List<?> queryTableNameList(JdbcOperation jdbcOperation, String databaseName) throws SQLException {
        String sql = "SELECT table_name AS tableName FROM information_schema.tables WHERE table_schema= ? ;";
        return jdbcOperation.queryForBean(sql, new Object[]{databaseName}, (RowMapper<String>) rs -> rs.getString(1));
    }

    @Override
    public List<Map<String, Object>> queryTableList(JdbcOperation jdbcOperation, CommonExample example) throws SQLException {
        if (null == example || !example.isDistinct()) {
            throw new RuntimeException("查询的表名不能为空..");
        }
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        if (null == example.getColumnNames() || 0 == example.getColumnNames().size()) {
            sql.append("SELECT * FROM `");
        } else {
            sql.append("SELECT ");
            List<String> columnNameList = example.getColumnNames();
            List<String> showColumnNameList = example.getShowColumnNames();
            for (int i = 0; i < columnNameList.size(); i++) {
                String columnName = columnNameList.get(i);
                String showColumnName = showColumnNameList.get(i);
                if (columnName.equals(showColumnName)) {
                    sql.append(columnName);
                } else {
                    sql.append(columnName);
                    sql.append(" AS '");
                    sql.append(showColumnName);
                    sql.append("'");
                }
                sql.append(", ");
            }
            sql.deleteCharAt(sql.length() - 2);
            sql.append("FROM `");
        }
        sql.append(example.getTableName()).append("`");
        generateWhere(example, sql, paramList);
        if (null != example.getOrderByClause() && "".equals(example.getOrderByClause())) {
            sql.append(" ORDER BY").append(example.getOrderByClause());
        }
        if (example.isLimit()) {
            sql.append(" LIMIT ?, ? ");
            paramList.add(example.getPageIndex());
            paramList.add(example.getPageSize());
        }
        System.out.println(sql.toString());
        return jdbcOperation.queryForMap(sql.toString(), paramList.toArray());
    }

    @Override
    public List<Map<String, Object>> queryTableList(JdbcOperation jdbcOperation, String sql) throws SQLException {
        return jdbcOperation.queryForMap(sql);
    }

    @Override
    public long queryTableCount(JdbcOperation jdbcOperation, CommonExample example) throws SQLException {
        if (example == null || !example.isDistinct()) {
            throw new RuntimeException("查询的表名不能为空..");
        }
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();
        sql.append("SELECT COUNT(*) FROM `").append(example.getTableName()).append("`");
        generateWhere(example, sql, paramList);
        System.out.println(sql.toString());
        return jdbcOperation.queryForLong(sql.toString(), paramList.toArray());
    }

    @Override
    public List<Column> queryTableColumnList(JdbcOperation jdbcOperation, CommonExample example) throws SQLException {
        return jdbcOperation.queryForColumn("SELECT * FROM `" + example.getTableName() + "` LIMIT 1");
    }

    @Override
    public int addRecord(JdbcOperation jdbcOperation, String tableName, Map<String, Object> params) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append(" (");
        List<Object> paramList = new ArrayList<>();
        int count = params.size();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            sql.append("`");
            sql.append(key);
            sql.append("`,");
            paramList.add(value);
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") VALUES (");
        for (int i = 0; i < count; i++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(");");
        System.out.println(sql.toString());
        return jdbcOperation.execute(sql.toString(), paramList.toArray());
    }


    /**
     * 生成where语句
     *
     * @param example   封装的参数
     * @param sql       where语句
     * @param paramList 封装的查询参数
     */
    private void generateWhere(CommonExample example, StringBuilder sql, List<Object> paramList) {
        if (example.isDistinct()) {
            sql.append(" WHERE ");
            for (CommonExample.Criteria criteria : example.getOredCriteria()) {
                if (criteria.isValid()) {
                    sql.append("( ");
                    for (CommonExample.Criterion criterion : criteria.getAllCriteria()) {
                        if (criterion.isNoValue()) {
                            sql.append(criterion.getCondition());
                            sql.append(" AND ");
                        }
                        if (criterion.isSingleValue()) {
                            sql.append(criterion.getCondition());
                            sql.append(" ? AND ");
                            paramList.add(criterion.getValue());
                        }
                        if (criterion.isBetweenValue()) {
                            sql.append(criterion.getCondition());
                            sql.append(" ? AND ? AND ");
                            paramList.add(criterion.getValue());
                            paramList.add(criterion.getSecondValue());
                        }
                        if (criterion.isListValue()) {
                            List<?> list = (List<?>) criterion.getValue();
                            if (null != list && list.size() > 0) {
                                sql.append(criterion.getCondition());
                                sql.append(" (");
                                for (Object obj : list) {
                                    sql.append("?,");
                                    paramList.add(obj);
                                }
                                sql.deleteCharAt(sql.length() - 1);
                                sql.append(") AND ");
                            }
                        }
                    }
                    sql.append(") OR ");
                    if (sql.indexOf("AND") != -1) {
                        sql.delete(sql.lastIndexOf("AND"), sql.lastIndexOf("AND") + 4);
                    }

                }
            }
            if (sql.indexOf("OR") != -1) {
                sql.delete(sql.lastIndexOf("OR"), sql.lastIndexOf("OR") + 3);
            } else {
                sql.delete(sql.length() - 7, sql.length());
            }
        }
    }
}
