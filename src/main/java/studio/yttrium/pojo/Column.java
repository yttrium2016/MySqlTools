package studio.yttrium.pojo;


import java.sql.Types;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/8
 * Time: 14:51
 */
public class Column {

    /**
     * 是否自动编号
     */
    private boolean autoIncrement;
    /**
     * 主要识别用
     */
    private String key;

    /**
     * 长度
     */
    private int columnSize;
    /**
     * 是否允许为空(NoNull 0,Nullable 1,Unknown 2)
     */
    private int isNullable;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 列名(可以用查询 AS 替换的)
     */
    private String columnLabel;
    /**
     * 类型(自定义)
     */
    private String type;
    /**
     * 字段类型
     */
    private int columnType;
    /**
     * 数据库字段类型
     */
    private String columnTypeName;

    /**
     * 查询显示的名字
     */
    private String showColumnName;

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(int isNullable) {
        this.isNullable = isNullable;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getSelectColumn() {
        return "`" + columnName + "`";
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
        this.type = SqlTypeToType(columnType);
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String getShowColumnName() {
        return showColumnName;
    }

    public void setShowColumnName(String showColumnName) {
        this.showColumnName = showColumnName;
    }

    private String SqlTypeToType(int sqlType) {
        String type = "String";
        switch (columnType) {
            //计算类型
            case Types.NUMERIC:
            case Types.DECIMAL:
                //计算出的值 用String吧
                type = "String";
                break;

            case Types.BIT:
                type = "Boolean";
                break;
            //数值类型
            case Types.SMALLINT:
            case Types.INTEGER:
                type = "Int";
                break;
            case Types.BIGINT:
                type = "Long";
                break;
            //浮点型
            case Types.REAL:
            case Types.FLOAT:
            case Types.DOUBLE:
                type = "Double";
                break;
            //Byte型
            case Types.TINYINT:
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                type = "Byte";
                break;
            //时间类型
            case Types.DATE:
            case Types.TIME:
            case Types.TIMESTAMP:
                type = "Date";
                break;
            //其他类型
            case Types.BLOB:
                //文件类型用String吧
                type = "String";
                break;
            case Types.CLOB:
                //文件类型用String吧
                type = "String";
                break;
        }
        return type;
    }
}
