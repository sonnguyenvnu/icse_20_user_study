package org.nutz.dao;

import org.nutz.castor.Castors;
import org.nutz.dao.impl.sql.NutSql;
import org.nutz.dao.impl.sql.ValueEscaper;
import org.nutz.dao.impl.sql.callback.*;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.lang.Mirror;
import org.nutz.lang.born.Borning;

/**
 * �??供了 Sql 相关的帮助函数
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public abstract class Sqls {

    private static final ValueEscaper ES_FLD_VAL = new ValueEscaper();
    private static final ValueEscaper ES_SQL_FLD = new ValueEscaper();
    private static final ValueEscaper ES_CND_VAL = new ValueEscaper();

    private static Borning<? extends Sql> sqlBorning;

    static {
        ES_FLD_VAL.add('\'', "''").add('\\', "\\\\").ready();
        ES_SQL_FLD.add('\'', "''").add('\\', "\\\\").add('$', "$$").add('@', "@@").ready();
        ES_CND_VAL.add('\'', "''").add('\\', "\\\\").add('_', "\\_").add('%', "\\%").ready();

        setSqlBorning(NutSql.class);
    }

    /**
     * 改�?� Sql 接�?�的实现类，如果你调用了这个方法，以�?�你�?调用本类其他帮助函数创建的 SQL 就是你�??供的这个实现类
     * <p>
     * 默认的，将用 org.nutz.dao.sql.impl.sql.NutSql 作为实现类
     * <p>
     * 你给出的 Sql 实现类必须有一个�?�访问的构造函数，接�?�一个字符串型�?�数
     * 
     * @param type
     *            你的 Sql 接�?�实现类
     */
    public static <T extends Sql> void setSqlBorning(Class<T> type) {
        sqlBorning = Mirror.me(type).getBorningByArgTypes(String.class);
    }

    /**
     * 创建了一个 Sql 对象。
     * <p>
     * 传入的 Sql 语�?�支�?�?��?和�?�数�?��?符：
     * <ul>
     * <li>�?��?： 格�?为 <b>$XXXX</b>，在执行�?，会被预先替�?�
     * <li>�?�数： 格�?为<b>@XXXX</b>，在执行�?，会替�?�为 '?'，用以构建 PreparedStatement
     * </ul>
     * 
     * @param sql
     *            Sql 语�?�
     * @return Sql 对象
     * 
     * @see org.nutz.dao.sql.Sql
     */
    public static Sql create(String sql) {
        return sqlBorning.born(sql);
    }

    /**
     * 创建了一个 Sql 对象。
     * <p>
     * 传入的 Sql 语�?�支�?�?��?和�?�数�?��?符：
     * <ul>
     * <li>�?��?： 格�?为 <b>$XXXX</b>，在执行�?，会被预先替�?�
     * <li>�?�数： 格�?为<b>@XXXX</b>，在执行�?，会替�?�为 '?'，用以构建 PreparedStatement
     * </ul>
     * 
     * @param fmt
     *            格�?字符，格�?�?�看 String.format 函数
     * @param args
     *            格�?字符串的�?�数
     * @return Sql 对象
     */
    public static Sql createf(String fmt, Object... args) {
        return create(String.format(fmt, args));
    }

    /**
     * 创建一个获�?��?�个实体对象的 Sql。
     * <p>
     * 这个函数除了执行 create(String)外，还会为这个 Sql 语�?�设置回调，用�?�获�?�实体对象。
     * <p>
     * <b style=color:red>注�?：</b>返回的 Sql 对象在执行�?，一定�?通过 setEntity 设置
     * 一个有效的实体，�?�则，会抛出异常。
     * 
     * @param sql
     *            Sql 语�?�
     * @return Sql 对象
     * 
     * @see org.nutz.dao.sql.Sql
     * @see org.nutz.dao.entity.Entity
     */
    public static Sql fetchEntity(String sql) {
        return create(sql).setCallback(callback.entity());
    }

    /**
     * 创建一个获�?��?�个 Record 对象的 Sql。
     * <p>
     * 这个函数除了执行 create(String)外，还会为这个 Sql 语�?�设置回调，用�?�获�?�实体对象。
     * 
     * @param sql
     *            Sql 语�?�
     * @return Sql 对象
     * 
     * @see org.nutz.dao.sql.Sql
     * @see org.nutz.dao.entity.Entity
     */
    public static Sql fetchRecord(String sql) {
        return create(sql).setCallback(callback.record());
    }

    /**
     * 创建一个获�?�整数的 Sql。
     * <p>
     * 这个函数除了执行 create(String)外，还会为这个 Sql 语�?�设置回调，用�?�获�?�整数值。
     * <p>
     * <b style=color:red>注�?：</b>你的 Sql 语�?�返回的 ResultSet 的第一列必须是数字
     * 
     * @param sql
     *            Sql 语�?�
     * @return Sql 对象
     * 
     * @see org.nutz.dao.sql.Sql
     */
    public static Sql fetchInt(String sql) {
        return create(sql).setCallback(callback.integer());
    }

    /**
     * @see #fetchInt(String)
     */
    public static Sql fetchLong(String sql) {
        return create(sql).setCallback(callback.longValue());
    }

    /**
     * @see #fetchInt(String)
     */
    public static Sql fetchFloat(String sql) {
        return create(sql).setCallback(callback.floatValue());
    }

    /**
     * @see #fetchInt(String)
     */
    public static Sql fetchDouble(String sql) {
        return create(sql).setCallback(callback.doubleValue());
    }

    /**
     * @see #fetchInt(String)
     */
    public static Sql fetchTimestamp(String sql) {
        return create(sql).setCallback(callback.timestamp());
    }

    /**
     * 创建一个获�?�字符串的 Sql。
     * <p>
     * 这个函数除了执行 create(String)外，还会为这个 Sql 语�?�设置回调，用�?�获�?�字符串。
     * <p>
     * <b style=color:red>注�?：</b>你的 Sql 语�?�返回的 ResultSet 的第一列必须是字符串
     * 
     * @param sql
     *            Sql 语�?�
     * @return Sql 对象
     * 
     * @see org.nutz.dao.sql.Sql
     */
    public static Sql fetchString(String sql) {
        return create(sql).setCallback(callback.str());
    }

    public static Sql queryString(String sql) {
        return create(sql).setCallback(callback.strs());
    }

    /**
     * 创建一个获�?�一组实体对象的 Sql。
     * <p>
     * 这个函数除了执行 create(String)外，还会为这个 Sql 语�?�设置回调，用�?�获�?�一组实体对象。
     * <p>
     * <b style=color:red>注�?：</b>返回的 Sql 对象在执行�?，一定�?通过 setEntity 设置
     * 一个有效的实体，�?�则，会抛出异常。
     * 
     * @param sql
     *            Sql 语�?�
     * @return Sql 对象
     * 
     * @see org.nutz.dao.sql.Sql
     * @see org.nutz.dao.entity.Entity
     */
    public static Sql queryEntity(String sql) {
        return create(sql).setCallback(callback.entities());
    }

    /**
     * 创建一个获�?�一组 Record 实体对象的 Sql。
     * <p>
     * 这个函数除了执行 create(String)外，还会为这个 Sql 语�?�设置回调，用�?�获�?�一组实体对象。
     * 
     * @param sql
     *            Sql 语�?�
     * @return Sql 对象
     */
    public static Sql queryRecord(String sql) {
        return create(sql).setCallback(callback.records());
    }

    /**
     * 一些内置的回调对象
     */
    public static CallbackFactory callback = new CallbackFactory();

    public static class CallbackFactory {
        /**
         * @return 从 ResultSet获�?�一个对象的回调对象
         */
        public SqlCallback entity() {
            return entity(null);
        }
        

        public SqlCallback entity(String prefix) {
            return new FetchEntityCallback(prefix);
        }

        /**
         * @return 从 ResultSet 获�?�一个 Record 的回调对象
         */
        public SqlCallback record() {
            return new FetchRecordCallback();
        }

        /**
         * @return 从 ResultSet 获�?�一个整数的回调对象
         */
        public SqlCallback integer() {
            return new FetchIntegerCallback();
        }

        /**
         * @return 从 ResultSet 获�?�一个长整型数的回调对象
         */
        public SqlCallback longValue() {
            return new FetchLongCallback();
        }

        /**
         * @return 从 ResultSet 获�?�一个浮点数的回调对象
         */
        public SqlCallback floatValue() {
            return new FetchFloatCallback();
        }

        /**
         * @return 从 ResultSet 获�?�一个�?�精度浮点数的回调对象
         */
        public SqlCallback doubleValue() {
            return new FetchDoubleCallback();
        }

        /**
         * @return 从 ResultSet 获�?�一个时间戳对象的回调对象
         */
        public SqlCallback timestamp() {
            return new FetchTimestampCallback();
        }

        /**
         * @return 从 ResultSet 获�?�一个字符串的回调对象
         */
        public SqlCallback str() {
            return new FetchStringCallback();
        }

        /**
         * @return 从 ResultSet 获得一个整数数组的回调对象
         */
        public SqlCallback ints() {
            return new QueryIntCallback();
        }

        /**
         * @return 从 ResultSet 获得一个长整型数组的回调对象
         */
        public SqlCallback longs() {
            return new QueryLongCallback();
        }

        /**
         * @return 从 ResultSet 获得一个字符串数组的回调对象
         */
        public SqlCallback strs() {
            return new QueryStringArrayCallback();
        }

        /**
         * @return 从 ResultSet 获得一个字符串列表的回调对象
         */
        public SqlCallback strList() {
            return new QueryStringCallback();
        }

        /**
         * @return 从 ResultSet获�?�一组对象的回调对象
         */
        public SqlCallback entities() {
            return entities(null);
        }
        
        public SqlCallback entities(String prefix) {
            return new QueryEntityCallback(prefix);
        }

        /**
         * @return 从 ResultSet 获�?�一组 Record 的回调对象
         */
        public SqlCallback records() {
            return new QueryRecordCallback();
        }

        public SqlCallback bool() {
            return new FetchBooleanCallback();
        }

        public SqlCallback bools() {
            return new QueryBooleanCallback();
        }
        
        /**
         * 与record()类似,但区分大�?写
         */
        public SqlCallback map() {
            return FetchMapCallback.me;
        }
        /**
         * 与records()类似,但区分大�?写
         * @return List<Map>回调
         */
        public SqlCallback maps() {
        	return QueryMapCallback.me;
        }

        /**
         * @return 从 ResultSet 获得一个blob的回调对象
         */
        public SqlCallback blob() {
            return new FetchBlobCallback();
        }
    }

    /**
     * 格�?化值，根�?�值的类型，生�? SQL 字段值的部分，它会考虑 SQL 注入
     * 
     * @param v
     *            字段值
     * @return 格�?化�?�的 Sql 字段值，�?�以直接拼装在 SQL 里�?�
     */
    public static CharSequence formatFieldValue(Object v) {
        if (null == v)
            return "NULL";
        else if (Sqls.isNotNeedQuote(v.getClass()))
            return Sqls.escapeFieldValue(v.toString());
        else
            return new StringBuilder("'").append(Sqls.escapeFieldValue(Castors.me().castToString(v)))
                                         .append('\'');
    }

    /**
     * 格�?化值，根�?�值的类型，生�? SQL 字段值的部分，它会考虑 SQL 注入，以�?� SQL 的 '$' 和 '@' 转义
     * 
     * @param v
     *            字段值
     * @return 格�?化�?�的 Sql 字段值，�?�以直接拼装在 SQL 里�?�
     */
    public static CharSequence formatSqlFieldValue(Object v) {
        if (null == v)
            return "NULL";
        else if (Sqls.isNotNeedQuote(v.getClass()))
            return Sqls.escapeSqlFieldValue(v.toString());
        else
            return new StringBuilder("'").append(Sqls.escapeSqlFieldValue(v.toString()))
                                         .append('\'');
    }

    /**
     * 将 SQL 的字段值进行转�?，�?�以用�?�防止 SQL 注入攻击
     * 
     * @param s
     *            字段值
     * @return 格�?化�?�的 Sql 字段值，�?�以直接拼装在 SQL 里�?�
     */
    public static CharSequence escapeFieldValue(CharSequence s) {
        if (null == s)
            return null;
        return ES_FLD_VAL.escape(s);
    }

    /**
     * 将 SQL 的字段值进行转�?，�?�以用�?�防止 SQL 注入攻击，<br>
     * �?�时，它也会将 Sql 的特殊标记 '$' 和 '@' 进行转译
     * 
     * @param s
     *            字段值
     * @return 格�?化�?�的 Sql 字段值，�?�以直接拼装在 SQL 里�?�
     */
    public static CharSequence escapeSqlFieldValue(CharSequence s) {
        if (null == s)
            return null;
        return ES_SQL_FLD.escape(s);
    }

    /**
     * 将 SQL 的 WHERE �?�件值进行转�?，�?�以用�?�防止 SQL 注入攻击
     * 
     * @param s
     *            字段值
     * @return 格�?化�?�的 Sql 字段值，�?�以直接拼装在 SQL 里�?�
     */
    public static CharSequence escapteConditionValue(CharSequence s) {
        if (null == s)
            return null;
        return ES_CND_VAL.escape(s);
    }

    /**
     * 判断一个值，在 SQL 中是�?�需�?�?�引�?�
     * 
     * @param type
     *            类型
     * @return 是�?�需�?加上�?�引�?�
     */
    public static boolean isNotNeedQuote(Class<?> type) {
        Mirror<?> me = Mirror.me(type);
        return me.isBoolean() || me.isPrimitiveNumber();
    }

}
