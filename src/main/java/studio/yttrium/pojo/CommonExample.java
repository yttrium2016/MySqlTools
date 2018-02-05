package studio.yttrium.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 自定义公用返回
 * Created with IntelliJ IDEA
 * Created By 杨振宇
 * Date: 2017/8/10
 * Time: 16:59
 */
public class CommonExample {

    /**
     * 排序
     */
    protected String orderByClause;

    /**
     * 实体名(表名)
     */
    protected String tableName;

    /**
     * 查找的字段 null默认为 *
     */
    protected List<String> columnNames;

    /**
     * 查找的字段重命名 默认和查找上一个一样
     */
    protected List<String> showColumnNames;

    /**
     * 是否分页
     */
    protected boolean limit;

    /**
     * 当前页(默认1)
     */
    protected int pageIndex;

    /**
     * 每页显示数据
     */
    protected int pageSize;

    /**
     * 不知道做什么用的
     */
    protected boolean distinct;

    /**
     * 每个条件
     */
    protected List<Criteria> oredCriteria;

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setLimitParam(int pageIndex, int pageSize) {
        limit = true;
        setPageSize(pageSize);
        setPageIndex(pageIndex);
    }

    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    protected void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex < 1 ? 0 : pageIndex;
    }

    protected void setPageSize(int pageSize) {
        if (pageSize < 1) {
            throw new RuntimeException("pageSize cannot < 1");
        }
        this.pageSize = pageSize;
    }

    public void addColumnName(String columnName) {
        if (columnName == null || "".equals(columnName)) {
            throw new RuntimeException("Add columnName cannot be null");
        }
        columnNames.add(columnName);
        showColumnNames.add(columnName);
    }

    public void addColumnName(String columnName,String showColumnName) {
        if (columnName == null || "".equals(columnName)) {
            throw new RuntimeException("Add columnName cannot be null");
        }
        columnNames.add(columnName);
        showColumnNames.add(showColumnName);
    }

    public CommonExample() {
        limit = false;
        oredCriteria = new ArrayList<>();
        columnNames = new ArrayList<>();
        showColumnNames = new ArrayList<>();
        distinct = false;
    }

    public CommonExample(String tableName) {
        limit = false;
        oredCriteria = new ArrayList<>();
        columnNames = new ArrayList<>();
        showColumnNames = new ArrayList<>();
        this.tableName = tableName;
        distinct = true;
    }

    public CommonExample(String tableName,int pageIndex, int pageSize) {
        limit = false;
        oredCriteria = new ArrayList<>();
        columnNames = new ArrayList<>();
        showColumnNames = new ArrayList<>();
        this.tableName = tableName;
        distinct = true;
        limit = true;
        setPageSize(pageSize);
        setPageIndex(pageIndex);
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getTableName() {
        return tableName;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
        this.showColumnNames = columnNames;
    }

    public void setColumnNames(List<String> columnNames,List<String> showColumnNames) {
        this.columnNames = columnNames;
        this.showColumnNames = showColumnNames;
    }

    public void setTableName(String tableName) {
        if (tableName == null || "".equals(tableName)) {
            throw new RuntimeException("tableName cannot be null");
        }
        this.tableName = tableName;
        distinct = true;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<String> getShowColumnNames() {
        return showColumnNames;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(CommonExample.Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public CommonExample.Criteria or() {
        CommonExample.Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public CommonExample.Criteria createCriteria() {
        CommonExample.Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected CommonExample.Criteria createCriteriaInternal() {
        CommonExample.Criteria criteria = new CommonExample.Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        columnNames.clear();
        showColumnNames.clear();
        orderByClause = null;
        limit = false;
        tableName = "";
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new CommonExample.Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new CommonExample.Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new CommonExample.Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        //通用

        public CommonExample.Criteria andColumnIsNull(String columnName) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " is null");
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnIsNotNull(String columnName) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " is not null");
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnIn(String columnName, List<? extends Object> values) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " in", values, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotIn(String columnName, List<? extends Object> values) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " not in", values, columnName);
            return (CommonExample.Criteria) this;
        }

        //int 类型

        public CommonExample.Criteria andColumnEqualTo(String columnName, Integer value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " =", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotEqualTo(String columnName, Integer value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <>", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThan(String columnName, Integer value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThanOrEqualTo(String columnName, Integer value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThan(String columnName, Integer value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThanOrEqualTo(String columnName, Integer value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnBetween(String columnName, Integer value1, Integer value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotBetween(String columnName, Integer value1, Integer value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " not between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        //String 类型

        public CommonExample.Criteria andColumnEqualTo(String columnName, String value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " =", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotEqualTo(String columnName, String value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <>", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThan(String columnName, String value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThanOrEqualTo(String columnName, String value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThan(String columnName, String value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThanOrEqualTo(String columnName, String value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnBetween(String columnName, String value1, String value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotBetween(String columnName, String value1, String value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " not between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLike(String columnName, String value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " like", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotLike(String columnName, String value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " not like", value, columnName);
            return (CommonExample.Criteria) this;
        }

        //Float 类型

        public CommonExample.Criteria andColumnEqualTo(String columnName, Float value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " =", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotEqualTo(String columnName, Float value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <>", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThan(String columnName, Float value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThanOrEqualTo(String columnName, Float value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThan(String columnName, Float value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThanOrEqualTo(String columnName, Float value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnBetween(String columnName, Float value1, Float value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotBetween(String columnName, Float value1, Float value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " not between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        //Double

        public CommonExample.Criteria andColumnEqualTo(String columnName, Double value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " =", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotEqualTo(String columnName, Double value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <>", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThan(String columnName, Double value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThanOrEqualTo(String columnName, Double value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThan(String columnName, Double value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThanOrEqualTo(String columnName, Double value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnBetween(String columnName, Double value1, Double value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotBetween(String columnName, Double value1, Double value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " not between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        //Date

        public CommonExample.Criteria andColumnEqualTo(String columnName, Date value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " =", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotEqualTo(String columnName, Date value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <>", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThan(String columnName, Date value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnGreaterThanOrEqualTo(String columnName, Date value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " >=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThan(String columnName, Date value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnLessThanOrEqualTo(String columnName, Date value) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " <=", value, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnBetween(String columnName, Date value1, Date value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

        public CommonExample.Criteria andColumnNotBetween(String columnName, Date value1, Date value2) {
            if (columnName == null) {
                throw new RuntimeException("Column for " + columnName + " cannot be null");
            }
            addCriterion(columnName + " not between", value1, value2, columnName);
            return (CommonExample.Criteria) this;
        }

    }

    public static class Criteria extends CommonExample.GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
