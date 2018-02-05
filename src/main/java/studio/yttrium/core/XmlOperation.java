package studio.yttrium.core;

import org.dom4j.Document;
import studio.yttrium.pojo.DatabaseInfo;
import studio.yttrium.pojo.SqlRecord;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2018/1/18
 * Time: 17:51
 */
public interface XmlOperation {

    void initXml();

    void writerXmlFile(Document document, String filePath);

    int addAndEditDatabaseInfo(DatabaseInfo info);

    int deleteDatabaseInfo(String primary);

    DatabaseInfo queryDatabaseInfo(String primary);

    List<DatabaseInfo> queryDatabaseInfoList();

    int addAndEditSqlRecord(String primary, SqlRecord record);

    int deleteSqlRecord(String primary, String id);

    SqlRecord querySqlRecord(String primary, String id);

    List<SqlRecord> querySqlRecordList(String primary);
}
