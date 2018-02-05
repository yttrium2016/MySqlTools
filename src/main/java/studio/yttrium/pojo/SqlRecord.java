package studio.yttrium.pojo;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/2/2
 * Time: 10:43
 */
public class SqlRecord {

    private String sql;
    private String name;
    private String id;
    private String databasePrimary;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatabasePrimary() {
        return databasePrimary;
    }

    public void setDatabasePrimary(String databasePrimary) {
        this.databasePrimary = databasePrimary;
    }
}
