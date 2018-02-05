package studio.yttrium.pojo;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/12/6
 * Time: 13:25
 */
public class DatabaseInfo {

    private static String DEFAULT_DRIVE = "com.mysql.cj.jdbc.Driver";
    private static String DEFAULT_HOST = "localhost";
    private static String DEFAULT_PORT = "3306";

    private String driver = DEFAULT_DRIVE;
    private String username;
    private String password;
    private String host = DEFAULT_HOST;
    private String port = DEFAULT_PORT;
    private String databaseName;

    public DatabaseInfo() {
    }

    public DatabaseInfo(String host, String port, String databaseName, String username, String password) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }


    public DatabaseInfo(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public DatabaseInfo(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getJdbcUrl() {
        StringBuilder url = new StringBuilder();
        url.append("jdbc:mysql://").append(host).append(":").append(port).append("/");
        if (StringUtils.isNotBlank(databaseName)) {
            url.append(databaseName);
        }
        url.append("?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false");
        return url.toString();
    }

    public String getJdbcBaseUrl() {
        return "jdbc:mysql://" + host + ":" + port + "/?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
    }

    public DatabaseInfo getBaseDatabaseInfo() {
        this.databaseName = "";
        return this;
    }

    public boolean isSearchDatabaseName() {
        return !StringUtils.isAnyBlank(host, port, username);
    }

    //唯一主键
    public String getPrimary() {
        return host + "_" + port + "_" + username;
    }

    public String getSQLPrimary() {
        return host + "_" + port + "_" + username + "_" + databaseName;
    }

    //判断主键是否相等
    public boolean equals(DatabaseInfo obj) {
        return getPrimary().equals(obj.getPrimary());
    }

    public DatabaseInfo databaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }
}
