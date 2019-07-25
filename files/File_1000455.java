package org.nutz.dao.sql;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.nutz.dao.FieldMatcher;
import org.nutz.dao.pager.Pager;

public class SqlContext {
	
	public static int DEFAULT_FETCH_SIZE = 0;

    private FieldMatcher fieldMatcher;

    private Object result;

    private int updateCount;

    private int fetchSize = DEFAULT_FETCH_SIZE;

    private int resultSetType;

    private Pager pager;

    private Map<String, Object> attrs;
    
    private int queryTimeout;

    public SqlContext() {
        // zozoh: 默认的，SQL 的游标类型是 TYPE_FORWARD_ONLY，�?�，使用�?�个数�?�库自有的分页语�?�
        // 但是如果数�?�库比较原始，你�?�以将游标类型设置�? TYPE_SCROLL_INSENSITIVE
        // 如果你还设置了 Pager，那么执行器应该使用 JDBC 游标的方�?�?�进行分页
        resultSetType = ResultSet.TYPE_FORWARD_ONLY;
    }

    public SqlContext attr(String name, Object value) {
        if (null == attrs) {
            attrs = new HashMap<String, Object>();
        }
        attrs.put(name, value);
        return this;
    }

    public Object attr(String name) {
        return null == attrs ? null : attrs.get(name);
    }

    public <T> T attr(Class<T> type) {
        return attr(type, type.getName());
    }

    @SuppressWarnings("unchecked")
    public <T> T attr(Class<T> classOfT, String name) {
        Object obj = attr(name);
        if (null == obj)
            return null;
        return (T) obj;
    }

    public boolean hasAttr(String name) {
        return null == attrs ? false : attrs.containsKey(name);
    }

    public Set<String> attrNames() {
        return null == attrs ? new HashSet<String>() : attrs.keySet();
    }

    public FieldMatcher getFieldMatcher() {
        return fieldMatcher;
    }

    public SqlContext setFieldMatcher(FieldMatcher matcher) {
        this.fieldMatcher = matcher;
        return this;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public int getResultSetType() {
        return resultSetType;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public void setResultSetType(int resultSetType) {
        this.resultSetType = resultSetType;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

}
