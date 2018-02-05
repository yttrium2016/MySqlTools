package studio.yttrium.core.impl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import studio.yttrium.core.XmlOperation;
import studio.yttrium.pojo.DatabaseInfo;
import studio.yttrium.pojo.SqlRecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/18
 * Time: 17:53
 */
@Component
public class XmlOperationImpl implements XmlOperation {

    private String webInfPath = null;
    @Value("${xml.database}")
    private String xmlDatabaseInfoName;
    //操作的工具类
    private SAXReader reader = new SAXReader();

    /**
     * 没有的话建立一个
     */
    @Override
    public void initXml() {
        File file = new File(getWebInfPath() + xmlDatabaseInfoName);
        if (!file.exists()) {
            Document document = DocumentHelper.createDocument();
            //建立根节点
            Element root = document.addElement("Root");
            root.addElement("DatabaseInfoList");
            root.addElement("SqlRecordList");
            //写入文件
            writerXmlFile(document, getWebInfPath() + xmlDatabaseInfoName);
        }
    }

    /**
     * 获取WebInf目录
     *
     * @return WebInf目录
     */
    private String getWebInfPath() {
        if (webInfPath == null) {
            webInfPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            if (webInfPath.startsWith("/") || webInfPath.startsWith("\\")) {
                webInfPath = webInfPath.substring(1);
            }
            if (webInfPath.indexOf("WEB-INF") > 0) {
                webInfPath = webInfPath.substring(0, webInfPath.indexOf("WEB-INF") + 8);
            }
        }
        return webInfPath;
    }

    /**
     * XML写入文件
     *
     * @param document 操作树
     * @param filePath 文件路径
     */
    @Override
    public void writerXmlFile(Document document, String filePath) {
        //输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置编码
        format.setEncoding("UTF-8");
        //XMLWriter 指定输出文件以及格式
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath)), "UTF-8"), format);
            //写入新文件
            writer.write(document);

        } catch (Exception e) {
            System.out.println("写入文件出错:" + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 结点添加一条数据
     *
     * @param document 操作树
     * @param info     DatabaseInfo实体
     * @return Document
     */
    private Document addDatabaseInfo(Document document, DatabaseInfo info) {

        Element rootElement = document.getRootElement();
        Element databaseInfoList = rootElement.element("DatabaseInfoList");
        Element databaseInfo = databaseInfoList.addElement("DatabaseInfo");

         /*private String driver = DEFAULT_DRIVE;
         private String username;
         private String password;
         private String host = DEFAULT_HOST;
         private String port = DEFAULT_PORT;
         private String databaseName;*/

        //以getJdbcBaseUrl为主键
        databaseInfo.addAttribute("primary", info.getPrimary());
        databaseInfo.addAttribute("date", "11");
        //driver
        Element driver = databaseInfo.addElement("driver");
        driver.setText(info.getDriver());
        //
        Element username = databaseInfo.addElement("username");
        username.setText(info.getUsername());
        //password
        Element password = databaseInfo.addElement("password");
        password.setText(info.getPassword());
        //host
        Element host = databaseInfo.addElement("host");
        host.setText(info.getHost());
        //port
        Element port = databaseInfo.addElement("port");
        port.setText(info.getPort());
        //databaseName
        Element databaseName = databaseInfo.addElement("databaseName");
        databaseName.setText(info.getDatabaseName());
        //jdbcUrl
        Element jdbcUrl = databaseInfo.addElement("jdbcUrl");
        jdbcUrl.setText(info.getJdbcUrl());
        //jdbcBaseUrl
        Element jdbcBaseUrl = databaseInfo.addElement("jdbcBaseUrl");
        jdbcBaseUrl.setText(info.getJdbcBaseUrl());

        return document;
    }

    /**
     * 结点删除一条记录
     *
     * @param document 操作树
     * @param primary  主键
     * @return Document
     */
    private Document deleteDatabaseInfo(Document document, String primary) {
        Element rootElement = document.getRootElement();
        Element databaseInfoList = rootElement.element("DatabaseInfoList");
        //同时迭代当前节点下面的所有子节点
        Iterator iterator = databaseInfoList.elementIterator();
        while (iterator.hasNext()) {
            Element e = (Element) iterator.next();
            String id = e.attributeValue("primary");
            if (primary.equals(id)) {
                databaseInfoList.remove(e);
                break;
            }
        }
        return document;
    }


    @Override
    public int addAndEditDatabaseInfo(DatabaseInfo info) {
        try {
            File file = new File(getWebInfPath() + xmlDatabaseInfoName);
            if (!file.exists()) {
                initXml();
            }
            Document document = reader.read(file);
            if (checkDatabaseInfoJdbcUrl(document, info.getPrimary())) {
                document = addDatabaseInfo(document, info);
            } else {
                document = addDatabaseInfo(deleteDatabaseInfo(document, info.getPrimary()), info);
            }
            writerXmlFile(document, getWebInfPath() + xmlDatabaseInfoName);
            return 1;
        } catch (DocumentException | RuntimeException e) {
            throw new RuntimeException("添加失败:" + e.getMessage());
        }
    }

    @Override
    public int deleteDatabaseInfo(String primary) {
        try {
            File file = new File(getWebInfPath() + xmlDatabaseInfoName);
            if (!file.exists()) {
                initXml();
            }
            Document document = reader.read(file);
            if (checkDatabaseInfoJdbcUrl(document, primary)) {
                document = deleteDatabaseInfo(document, primary);
                writerXmlFile(document, getWebInfPath() + xmlDatabaseInfoName);
                return 1;
            } else {
                return 0;
            }
        } catch (DocumentException e) {
            throw new RuntimeException("删除DatabaseInfo失败" + e.getMessage());
        }
    }

    @Override
    public DatabaseInfo queryDatabaseInfo(String primary) {
        try {
            File file = new File(getWebInfPath() + xmlDatabaseInfoName);
            if (!file.exists()) {
                initXml();
            }
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element databaseInfoList = rootElement.element("DatabaseInfoList");
            Iterator iterator = databaseInfoList.elementIterator();
            while (iterator.hasNext()) {
                Element item = (Element) iterator.next();
                String id = item.attributeValue("primary");
                if (id.equals(primary)) {
                    DatabaseInfo info = new DatabaseInfo();
                    info.setDriver(item.elementText("driver"));
                    info.setUsername(item.elementText("username"));
                    info.setPassword(item.elementText("password"));
                    info.setHost(item.elementText("host"));
                    info.setPort(item.elementText("port"));
                    info.setDatabaseName(item.elementText("databaseName"));
                    return info;
                }
            }
            return null;
        } catch (DocumentException e) {
            throw new RuntimeException("查询DatabaseInfo通过primary失败" + e.getMessage());
        }
    }

    @Override
    public List<DatabaseInfo> queryDatabaseInfoList() {
        try {
            File file = new File(getWebInfPath() + xmlDatabaseInfoName);
            if (!file.exists()) {
                initXml();
            }
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();

            Element databaseInfoList = rootElement.element("DatabaseInfoList");
            List<DatabaseInfo> list = new ArrayList<>();
            Iterator iterator = databaseInfoList.elementIterator();
            while (iterator.hasNext()) {
                Element item = (Element) iterator.next();
                DatabaseInfo info = new DatabaseInfo();
                info.setDriver(item.elementText("driver"));
                info.setUsername(item.elementText("username"));
                info.setPassword(item.elementText("password"));
                info.setHost(item.elementText("host"));
                info.setPort(item.elementText("port"));
                info.setDatabaseName(item.elementText("databaseName"));
                list.add(info);
            }
            return list;
        } catch (DocumentException e) {
            throw new RuntimeException("读取所有DatabaseInfo失败:" + e.getMessage());
        }
    }

    @Override
    public int addAndEditSqlRecord(String primary, SqlRecord record) {
        try {
            File file = new File(getWebInfPath() + xmlDatabaseInfoName);
            if (!file.exists()) {
                initXml();
            }
            Document document = reader.read(file);
            document = addSqlRecord(deleteSqlRecord(document, primary, record.getId()), primary, record);
            writerXmlFile(document, getWebInfPath() + xmlDatabaseInfoName);
            return 1;
        } catch (DocumentException | RuntimeException e) {
            throw new RuntimeException("添加失败:" + e.getMessage());
        }
    }

    /**
     * 结点添加一条数据
     *
     * @param document 操作树
     * @param primary  DatabaseInfo主键
     * @param record   SqlRecord实体
     * @return Document
     */
    private Document addSqlRecord(Document document, String primary, SqlRecord record) {

        Element rootElement = document.getRootElement();
        Element sqlRecordList = rootElement.element("SqlRecordList");
        Element sqlRecord = sqlRecordList.addElement("SqlRecord");

         /* private String sql;
            private String name;
            private String id;
            private String databasePrimary;*/

        //以getJdbcBaseUrl为主键
        sqlRecord.addAttribute("primary", primary);
        sqlRecord.addAttribute("id", record.getId());
        sqlRecord.addAttribute("date", "11");
        //name
        Element name = sqlRecord.addElement("name");
        name.setText(record.getName());
        //sql
        Element sql = sqlRecord.addElement("sql");
        sql.setText(record.getSql());
        //databasePrimary
        Element databasePrimary = sqlRecord.addElement("databasePrimary");
        databasePrimary.setText(record.getDatabasePrimary());

        return document;
    }

    /**
     * 结点删除一条记录
     *
     * @param document 操作树
     * @param primary  主键
     * @return Document
     */
    private Document deleteSqlRecord(Document document, String primary, String id) {
        Element rootElement = document.getRootElement();
        Element sqlRecordList = rootElement.element("SqlRecordList");
        //同时迭代当前节点下面的所有子节点
        Iterator iterator = sqlRecordList.elementIterator();
        while (iterator.hasNext()) {
            Element e = (Element) iterator.next();
            String dbPrimary = e.attributeValue("primary");
            String dbId = e.attributeValue("id");
            if (primary.equals(dbPrimary) && id.equals(dbId)) {
                sqlRecordList.remove(e);
                break;
            }
        }
        return document;
    }

    @Override
    public int deleteSqlRecord(String primary, String id) {
        try {
            File file = new File(getWebInfPath() + xmlDatabaseInfoName);
            if (!file.exists()) {
                initXml();
            }
            Document document = reader.read(file);
            document = deleteSqlRecord(document, primary, id);
            writerXmlFile(document, getWebInfPath() + xmlDatabaseInfoName);
            return 1;
        } catch (DocumentException e) {
            throw new RuntimeException("删除DatabaseInfo失败" + e.getMessage());
        }
    }

    @Override
    public SqlRecord querySqlRecord(String primary, String id) {
        try {
            File file = new File(getWebInfPath() + xmlDatabaseInfoName);
            if (!file.exists()) {
                initXml();
            }
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();
            Element sqlRecordList = rootElement.element("SqlRecordList");
            Iterator iterator = sqlRecordList.elementIterator();
            while (iterator.hasNext()) {
                Element item = (Element) iterator.next();
                String dbPrimary = item.attributeValue("primary");
                String dbId = item.attributeValue("id");
                if (dbPrimary.equals(primary) && dbId.equals(id)) {
                    SqlRecord record = new SqlRecord();
                    record.setId(id);
                    record.setDatabasePrimary(item.elementText("databasePrimary"));
                    record.setName(item.elementText("name"));
                    record.setSql(item.elementText("sql"));
                    return record;
                }
            }
            return null;
        } catch (DocumentException e) {
            throw new RuntimeException("查询SqlRecord通过primary失败" + e.getMessage());
        }
    }

    @Override
    public List<SqlRecord> querySqlRecordList(String primary) {
        try {
            File file = new File(getWebInfPath() + xmlDatabaseInfoName);
            if (!file.exists()) {
                initXml();
            }
            Document document = reader.read(file);
            Element rootElement = document.getRootElement();

            Element sqlRecordList = rootElement.element("SqlRecordList");
            List<SqlRecord> list = new ArrayList<>();
            Iterator iterator = sqlRecordList.elementIterator();
            while (iterator.hasNext()) {
                Element item = (Element) iterator.next();
                String dbPrimary = item.attributeValue("primary");
                if (dbPrimary.equals(primary)) {
                    SqlRecord record = new SqlRecord();
                    record.setId(item.attributeValue("id"));
                    record.setDatabasePrimary(item.elementText("databasePrimary"));
                    record.setName(item.elementText("name"));
                    record.setSql(item.elementText("sql"));
                    list.add(record);
                }
            }
            return list;
        } catch (DocumentException e) {
            throw new RuntimeException("读取所有SqlRecordList失败:" + e.getMessage());
        }
    }

    /**
     * 检查名字是否重复
     *
     * @param document   xml树
     * @param primaryKey 主键
     * @return boolean
     */

    private boolean checkDatabaseInfoJdbcUrl(Document document, String primaryKey) {
        if (document == null) {
            return false;
        }
        Element rootElement = document.getRootElement();
        Element databaseInfoList = rootElement.element("DatabaseInfoList");
        //同时迭代当前节点下面的所有子节点
        Iterator iterator = databaseInfoList.elementIterator();
        while (iterator.hasNext()) {
            Element e = (Element) iterator.next();
            String primary = e.attributeValue("primary");
            if (primaryKey.equals(primary)) {
                return false;
            }
        }
        return true;
    }

}
