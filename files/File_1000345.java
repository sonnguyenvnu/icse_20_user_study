package org.nutz.dao;

import java.lang.reflect.Array;
import java.util.Collection;

import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.MappingField;
import org.nutz.dao.impl.SimpleNesting;
import org.nutz.dao.jdbc.ValueAdaptor;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.GroupBy;
import org.nutz.dao.sql.OrderBy;
import org.nutz.dao.sql.Pojo;
import org.nutz.dao.util.Daos;
import org.nutz.dao.util.cnd.SimpleCondition;
import org.nutz.dao.util.cri.Exps;
import org.nutz.dao.util.cri.NestExps;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.segment.CharSegment;
import org.nutz.lang.util.Callback2;

/**
 * 是 Condition 的一个实现，这个类给你比较方便的方法�?�构建 Condition 接�?�的实例。
 *
 * <h4>在 Dao 接�?�中使用</h4><br>
 *
 * 比如一个通常的查询:
 * <p>
 * List<Pet> pets = dao.query(Pet.class,
 * Cnd.where("name","LIKE","B%").asc("name"), null);
 *
 * <h4>链�?赋值示例</h4><br>
 * Cnd.where("id", ">", 34).and("name","LIKE","T%").asc("name"); <br>
 * 相当于<br>
 * WHERE id>34 AND name LIKE 'T%' ORDER BY name ASC
 * <p>
 * Cnd.orderBy().desc("id"); <br>
 * 相当于<br>
 * ORDER BY id DESC
 *
 * <p/> <b>带括�?�的�?�件语�?�<b/> where (name="wendal" or age<18) and location != "地�?�" <p/>
 * <code>Cnd.where(Cnd.exps("name", "=", "wendal").or("age", "<", 18)).and("location", "!=", "地�?�")</code>
 *
 * <p/><b>�?��?�?�件,直接拼入sql,�?�?�任何转义. Oracle的日期传Date对象,而�?�用to_date等数�?�库方法</b><p/>
 * <code>Cnd.where(new Static("ct < to_date('2015-06-26')")).and(...........) </code>
 * <p/>
 *
 * <p/><b>between用法</b><p/>
 * <code>Cnd.where("age", "between", new Object[]{19,29}).and(...........) </code>
 * <p/>
 *
 * <h4 style=color:red>你还需�?知�?�的是:</h4><br>
 * <ul>
 * <li>你设置的字段�??，是 java 的字段�?? -- 如果 Entity 里有，那么会被转�?��?数�?�库字段�??
 * <li>如果你设置的是 entity 中�?存在的 java 字段�??，则被认为是数�?�库字段�??，将直接使用
 * <li>你的值，如果是字符串，或者其他类字符串对象（�?�? CharSequence），那么在转�?��? SQL 时，会正确被�?�引�?�包裹
 * <li>你的值如果是�?�?��?�解的自定义对象，会被转化�?字符串处�?�
 * </ul>
 *
 * @author zozoh(zozohtnt@gmail.com)
 * @author 蛋蛋 [TopCoderMyDream@gmail.com]
 * @see org.nutz.dao.Condition
 */
public class Cnd implements OrderBy, Criteria, GroupBy {

    private static final long serialVersionUID = 1L;

    /**
     * 用字符串和�?�数格�?化出一个�?�件语�?�,注�?,�?会抹除特殊字符
     * @param format sql�?�件
     * @param args �?�数
     * @return �?�件对象
     */
    public static Condition format(String format, Object... args) {
        return Strings.isBlank(format) ? null : new SimpleCondition(format,
                args);
    }

    /***
     * 直接用字符串生�?一个�?�件对象
     * @param str sql�?�件
     * @return �?�件对象
     */
    public static Condition wrap(String str) {
        return Strings.isBlank(str) ? null : new SimpleCondition((Object) str);
    }

    /**
     * 使用CharSegment拼装一个�?�件对象
     * @param sql sql模�?�
     * @param value �?�数
     * @return �?�件对象
     * @see org.nutz.lang.segment.CharSegment
     */
    public static Condition wrap(String sql, Object value) {
        return Strings.isBlank(sql) ? null
                : new SimpleCondition(new CharSegment(sql).setBy(value));
    }

    /**
     * 生�?一个�?�件表达�?
     * @param name Java属性或字段�??称
     * @param op   �?作符,�?�以是 = like 等等
     * @param value �?�数值.
     * @return �?�件表达�?
     */
    public static SqlExpression exp(String name, String op, Object value) {
        if(value!=null && value instanceof Nesting){
            return NestExps.create(name, op, (Nesting) value);
        }
        return Exps.create(name, op, value);
    }

    /**
     * 生�?一个�?�件表达�?组
     * @param name Java属性或字段�??称
     * @param op   �?作符,�?�以是 = like 等等
     * @param value �?�数值.
     * @return �?�件表达�?组
     */
    public static SqlExpressionGroup exps(String name, String op, Object value) {
        return exps(exp(name, op, value));
    }

    /**
     * 将一个�?�件表达�?�?装为�?�件表达�?组
     * @param exp 原本的�?�件表达�?
     * @return �?�件表达�?组
     */
    public static SqlExpressionGroup exps(SqlExpression exp) {
        return new SqlExpressionGroup().and(exp);
    }

    /**
     * 生�?一个新的Cnd实例
     * @param name java属性或字段�??称, 推�??用Java属性
     * @param op �?作符,�?�以是= like等等
     * @param value �?�数值. 如果�?作符是between,�?�数值需�?是new Object[]{12,39}形�?
     * @return Cnd实例
     */
    public static Cnd where(String name, String op, Object value) {
        return new Cnd(Cnd.exp(name, op, value));
    }

    /**
     * 用一个�?�件表达�?构建一个Cnd实例
     * @param e �?�件表达�?
     * @return Cnd实例
     */
    public static Cnd where(SqlExpression e) {
        return new Cnd(e);
    }

    /**
     * 生�?一个简�?��?�件对象
     */
    public static SimpleCriteria cri() {
        return new SimpleCriteria();
    }

    /**
     * �?�纯生�?一个Orderby�?�件
     * @return OrderBy实例
     */
    public static OrderBy orderBy() {
        return new Cnd();
    }

    /**
     * @return 一个 Cnd 的实例
     * @deprecated Since 1.b.50 �?推�??使用这个函数构建 Cnd 的实例，因为看起�?�语�?�?明的样�?
     */
    public static Cnd limit() {
        return new Cnd();
    }

    /**
     * @return 一个 Cnd 的实例
     */
    public static Cnd NEW() {
        return new Cnd();
    }

    /**
     * 用SimpleCriteria生�?一个Cnd实例
     * @param cri SimpleCriteria实例
     * @return Cnd实例
     */
    public static Cnd byCri(SimpleCriteria cri) {
        return new Cnd().setCri(cri);
    }

    /*------------------------------------------------------------------*/

    protected SimpleCriteria cri;

    protected Cnd() {
        cri = new SimpleCriteria();
    }

    private Cnd setCri(SimpleCriteria cri) {
        this.cri = cri;
        return this;
    }

    /**
     * 获�?�内部的where属性
     * @return SimpleCriteria实例
     */
    public SimpleCriteria getCri() {
        return cri;
    }

    protected Cnd(SqlExpression exp) {
        this();
        cri.where().and(exp);
    }

    /**
     * 按Java属性/字段属性进行�?��?. <b>�?进行SQL特殊字符抹除<b/>  cnd.asc("age")
     * @param name Java属性/字段属性
     */
    public OrderBy asc(String name) {
        cri.asc(name);
        return this;
    }

    /**
     * 按Java属性/字段属性进行�?�?. <b>�?进行SQL特殊字符抹除<b/> cnd.desc("age")
     * @param name Java属性/字段属性
     */
    public OrderBy desc(String name) {
        cri.desc(name);
        return this;
    }

    /**
     * 当dir为asc时判断为�?��?,�?�则判定为�?�?. cnd.orderBy("age", "asc")
     * @param name Java属性/字段属性
     * @param dir asc或其他
     * @return OrderBy实例,事实上就是当�?对象
     */
    public OrderBy orderBy(String name, String dir) {
        if ("asc".equalsIgnoreCase(dir)) {
            this.asc(name);
        } else {
            this.desc(name);
        }
        return this;
    }

    /**
     * Cnd.where(...).and(Cnd.exp(.........)) 或 Cnd.where(...).and(Cnd.exps(.........))
     * @param exp �?�件表达�?
     * @return 当�?对象,用于链�?调用
     */
    public Cnd and(SqlExpression exp) {
        cri.where().and(exp);
        return this;
    }

    /**
     * Cnd.where(...).and("age", "<", 40)
     * @param name Java属性或字段�??称,推�??用Java属性,如果有的�?
     * @param op �?作符,�?�以是 = like等
     * @param value �?�数值, 如果是between的�?需�?传入new Object[]{19,28}
     * @return 当�?对象,用于链�?调用
     */
    public Cnd and(String name, String op, Object value) {
        return and(Cnd.exp(name, op, value));
    }

    /**
     * Cnd.where(...).or(Cnd.exp(.........)) 或 Cnd.where(...).or(Cnd.exps(.........))
     * @param exp �?�件表达�?
     * @return 当�?对象,用于链�?调用
     */
    public Cnd or(SqlExpression exp) {
        cri.where().or(exp);
        return this;
    }

    /**
     * Cnd.where(...).or("age", "<", 40)
     * @param name Java属性或字段�??称,推�??用Java属性,如果有的�?
     * @param op �?作符,�?�以是 = like等
     * @param value �?�数值, 如果是between的�?需�?传入new Object[]{19,28}
     * @return 当�?对象,用于链�?调用
     */
    public Cnd or(String name, String op, Object value) {
        return or(Cnd.exp(name, op, value));
    }

    /**
     * and一个�?�件表达�?并且�?��?�
     * @param exp �?�件表达�?
     * @return 当�?对象,用于链�?调用
     */
    public Cnd andNot(SqlExpression exp) {
        cri.where().and(exp.setNot(true));
        return this;
    }

    /**
     * and一个�?�件,并且�?��?�
     * @param name Java属性或字段�??称,推�??用Java属性,如果有的�?
     * @param op �?作符,�?�以是 = like等
     * @param value �?�数值, 如果是between的�?需�?传入new Object[]{19,28}
     * @return 当�?对象,用于链�?调用
     */
    public Cnd andNot(String name, String op, Object value) {
        return andNot(Cnd.exp(name, op, value));
    }

    /**
     * @see Cnd#andNot(SqlExpression)
     */
    public Cnd orNot(SqlExpression exp) {
        cri.where().or(exp.setNot(true));
        return this;
    }

    /**
     * @see Cnd#andNot(String, String, Object)
     */
    public Cnd orNot(String name, String op, Object value) {
        return orNot(Cnd.exp(name, op, value));
    }

    /**
     * 获�?�分页对象,默认是null
     */
    public Pager getPager() {
        return cri.getPager();
    }

    /**
     * 根�?�实体Entity将本对象转化为sql语�?�, �?�件表达�?中的name属性将转化为数�?�库字段�??称
     */
    public String toSql(Entity<?> en) {
        return cri.toSql(en);
    }

    /**
     * 判断两个Cnd是�?�相等
     */
    public boolean equals(Object obj) {
        return cri.equals(obj);
    }

    /**
     * 直接转为SQL语�?�, 如果setPojo未曾调用, �?�件表达�?中的name属性未映射为数�?�库字段
     */
    public String toString() {
        return cri.toString();
    }

    /**
     * 关�?�的Pojo,�?�以用于toString时的name属性映射
     */
    public void setPojo(Pojo pojo) {
        cri.setPojo(pojo);
    }

    /**
     * 获�?�已设置的Pojo, 默认为null
     */
    public Pojo getPojo() {
        return cri.getPojo();
    }

    public void joinSql(Entity<?> en, StringBuilder sb) {
        cri.joinSql(en, sb);
    }

    public int joinAdaptor(Entity<?> en, ValueAdaptor[] adaptors, int off) {
        return cri.joinAdaptor(en, adaptors, off);
    }

    public int joinParams(Entity<?> en, Object obj, Object[] params, int off) {
        return cri.joinParams(en, obj, params, off);
    }

    public int paramCount(Entity<?> en) {
        return cri.paramCount(en);
    }

    /**
     * 获�?�Cnd中的where部分,注�?,对SqlExpressionGroup的修改也会�??映到Cnd中,因为是�?�一个对象
     */
    public SqlExpressionGroup where() {
        return cri.where();
    }

    /**
     * 分组
     * @param names java属性或数�?�库字段�??称
     */
    public GroupBy groupBy(String... names) {
        cri.groupBy(names);
        return this;
    }

    /**
     * 分组中的having�?�件
     * @param cnd �?�件语�?�
     */
    public GroupBy having(Condition cnd) {
        cri.having(cnd);
        return this;
    }

    /**
     * �?�独获�?�排�?�?�件,建议使用asc或desc,而�?�直接�?�出排�?�?�件. �?�出的对象仅包�?�分组�?�件, �?包�?�where等部分
     */
    public OrderBy getOrderBy() {
        return cri.getOrderBy();
    }

    /**
     * 分页
     * @param pageNumber 页数, 若�?于1则代表全部记录
     * @param pageSize �?页数�?
     * @return 当�?对象,用于链�?调用
     */
    public Cnd limit(int pageNumber, int pageSize) {
        cri.setPager(pageNumber, pageSize);
        return this;
    }

    /**
     * 设置�?页大�?,并设置页数为1
     * @param pageSize �?页大�?
     * @return 当�?对象,用于链�?调用
     */
    @Deprecated
    public Cnd limit(int pageSize) {
        cri.setPager(1, pageSize);
        return this;
    }

    /**
     * 直接设置分页对象, �?�以new Pager或dao.createPager得到
     * @param pager 分页对象
     * @return 当�?对象,用于链�?调用
     */
    public Cnd limit(Pager pager) {
        cri.setPager(pager);
        return this;
    }

    protected static FieldMatcher dftFromFieldMatcher = new FieldMatcher().setIgnoreNull(true).setIgnoreZero(true);

    /**
     * 用默认规则(忽略零值和空值)生�?Cnd实例
     * @param dao Dao实例,�?能为null
     * @param obj 对象, 若为null,则返回值为null, �?�?�以是Class/字符串/数值/布尔类型
     * @return Cnd实例
     */
    public static Cnd from(Dao dao, Object obj) {
        return from(dao, obj, dftFromFieldMatcher);
    }

    /**
     * 根�?�一个对象生�?Cnd�?�件, FieldMatcher详细控制.<p/>
     * <code>assertEquals(" WHERE name='wendal' AND age=0", Cnd.from(dao, pet, FieldMatcher.make("age|name", null, true).setIgnoreDate(true)).toString());</code>
     * @param dao Dao实例
     * @param obj 基对象,�?�?�以是Class,字符串,数值和Boolean
     * @param matcher 过滤字段属性, �?��?置哪些字段�?�用/�?�?�用/是�?�忽略空值/是�?�忽略0值/是�?�忽略java.util.Date类�?�其�?类的对象/是�?�忽略@Id所标注的主键属性/是�?�忽略 \@Name 所标注的主键属性/是�?�忽略 \@Pk 所引用的�?�?�主键
     * @return Cnd�?�件
     */
    public static Cnd from(Dao dao, Object obj, FieldMatcher matcher) {
        final SqlExpressionGroup exps = new SqlExpressionGroup();
        boolean re = Daos.filterFields(obj, matcher, dao, new Callback2<MappingField, Object>() {
            public void invoke(MappingField mf, Object val) {
                exps.and(mf.getName(), "=", val);
            }
        });
        if (re)
            return Cnd.where(exps);
        return null;
    }

    /**
     * 若value为null/空白字符串/空集�?�/空数组,则本�?�件�?添加.
     * @see Cnd#and(String, String, Object)
     */
    public Cnd andEX(String name, String op, Object value) {
        return and(Cnd.expEX(name, op, value));
    }

    /**
     * 若value为null/空白字符串/空集�?�/空数组,则本�?�件�?添加.
     * @see Cnd#or(String, String, Object)
     */
    public Cnd orEX(String name, String op, Object value) {
        return or(Cnd.expEX(name, op, value));
    }

    public static SqlExpression expEX(String name, String op, Object value) {
        if (_ex(value))
            return null;
        return Cnd.exp(name, op, value);
    }

    @SuppressWarnings("rawtypes")
    public static boolean _ex(Object value) {
        return value == null
                || (value instanceof CharSequence && Strings.isBlank((CharSequence)value))
                || (value instanceof Collection && ((Collection)value).isEmpty())
                || (value.getClass().isArray() && Array.getLength(value) == 0);
    }

    public GroupBy getGroupBy() {
        return cri.getGroupBy();
    }

    /**
     * 构造一个�?�嵌套�?�件，需�?dao支�?�?能映射类与表和属性与列
     */
    public static Nesting nst(Dao dao){
        return new SimpleNesting(dao);
    }

    /**
     * 克隆当�?Cnd实例
     * @return 一模一样的兄弟
     */
    public Cnd clone() {
        return Lang.fromBytes(Lang.toBytes(this),Cnd.class);
    }
    
    /**
     * 仅拷�?where�?�件, �?拷�?排�?/分组/分页
     */
    public Cnd cloneWhere() {
        return Cnd.where(this.cri.where().clone());
    }
}
