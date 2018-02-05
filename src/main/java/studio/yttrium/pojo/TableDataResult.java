package studio.yttrium.pojo;


/**
 * dataTables 1.10.15版本默认服务器返回值
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/8/17
 * Time: 16:14
 */
public class TableDataResult {

    /**
     * 第几次请求和返回
     */
    private int draw;

    /**
     * 数据库总的数据数目
     */
    private long recordsTotal;

    /**
     * 数据库筛选后的数据数目
     */
    private long recordsFiltered;

    /**
     * 数据(List)
     */
    private Object data;

    public TableDataResult() {
    }

    public TableDataResult(int draw, long recordsTotal, long recordsFiltered, Object data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
}
