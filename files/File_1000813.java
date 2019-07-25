package org.nutz.service;

import java.sql.ResultSet;
import java.util.List;

import org.nutz.dao.Chain;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.FieldMatcher;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.Each;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 实体Service抽象类. 属于辅助类. 任何方法被调用�?,必须确�?Dao实例已�?传入
 * 
 * @author wendal(wendal1985@gmail.com)
 *
 * @param <T>
 *            实体类型
 */
public abstract class EntityService<T> extends Service {

    private Mirror<T> mirror;

    private static final Log log = Logs.get();

    /**
     * 本抽象类能�??供一些帮助方法,�?少�?�?写实体类型的麻烦
     */
    @SuppressWarnings("unchecked")
    public EntityService() {
        try {
            Class<T> entryClass = (Class<T>) Mirror.getTypeParam(getClass(), 0);
            mirror = Mirror.me(entryClass);
            if (log.isDebugEnabled())
                log.debugf("Get TypeParams for self : %s", entryClass.getName());
        }
        catch (Throwable e) {
            if (log.isWarnEnabled())
                log.warn("!!!Fail to get TypeParams for self!", e);
        }
    }

    /**
     * 新建并传入Dao实例
     * 
     * @param dao
     *            Dao实例
     */
    public EntityService(Dao dao) {
        this();
        this.setDao(dao);
    }

    /**
     * 新建并传入Dao实例,�?�时指定实体类型
     * 
     * @param dao
     *            Dao实例
     * @param entityType
     *            实体类型
     */
    public EntityService(Dao dao, Class<T> entityType) {
        setEntityType(entityType);
        setDao(dao);
    }

    /**
     * 获�?�实体类型的�??射�?装类实例
     * 
     * @return �??射�?装类实例
     */
    public Mirror<T> mirror() {
        return mirror;
    }

    /**
     * 设置新的实体类型, �?少调用
     * 
     * @param classOfT
     */
    @SuppressWarnings("unchecked")
    public <C extends T> void setEntityType(Class<C> classOfT) {
        mirror = (Mirror<T>) Mirror.me(classOfT);
    }

    /**
     * 获�?�实体的Entity
     * 
     * @return 实体的Entity
     */
    public Entity<T> getEntity() {
        return dao().getEntity(mirror.getType());
    }

    /**
     * 获�?�实体类型
     * 
     * @return 实体类型
     */
    public Class<T> getEntityClass() {
        return mirror.getType();
    }

    /**
     * 批�?删除
     * 
     * @param cnd
     *            �?�件
     * @return 删除的�?�数
     */
    public int clear(Condition cnd) {
        return dao().clear(getEntityClass(), cnd);
    }

    /**
     * 全表删除
     * 
     * @return 删除的�?�数
     */
    public int clear() {
        return dao().clear(getEntityClass(), null);
    }

    /**
     * 根�?��?�件分页查询
     * 
     * @param cnd
     *            查询�?�件
     * @param pager
     *            分页
     * @return 查询结果
     */
    public List<T> query(Condition cnd, Pager pager) {
        return (List<T>) dao().query(getEntityClass(), cnd, pager);
    }

    /**
     * �??历�?�件分页查询结果
     * 
     * @param cnd
     *            查询�?�件
     * @param pager
     *            分页
     * @param callback
     *            �??历回调
     * @return �??历的总�?�数
     */
    public int each(Condition cnd, Pager pager, Each<T> callback) {
        return dao().each(getEntityClass(), cnd, pager, callback);
    }

    /**
     * 根�?��?�件统计符�?��?�件的记录数
     * 
     * @param cnd
     *            查询�?�件
     * @return 记录数
     */
    public int count(Condition cnd) {
        return dao().count(getEntityClass(), cnd);
    }

    /**
     * 全表的总记录数
     * 
     * @return 总记录数
     */
    public int count() {
        return dao().count(getEntityClass());
    }

    /**
     * 查出符�?��?�件的第一�?�记录
     * 
     * @param cnd
     *            查询�?�件
     * @return 实体,如�?存在则为null
     */
    public T fetch(Condition cnd) {
        return dao().fetch(getEntityClass(), cnd);
    }

    /**
     * �?�?�主键专用
     * 
     * @param pks
     *            键值
     * @return 对象 T
     */
    public T fetchx(Object... pks) {
        return dao().fetchx(getEntityClass(), pks);
    }

    /**
     * �?�?�主键专用
     * 
     * @param pks
     *            键值
     * @return 对象 T
     */
    public boolean exists(Object... pks) {
        return null != fetchx(pks);
    }

    /**
     * 批�?更新
     * 
     * @param chain
     *            设置值的键值对
     * @param cnd
     *            需�?更新的�?�件语�?�
     */
    public int update(Chain chain, Condition cnd) {
        return dao().update(getEntityClass(), chain, cnd);
    }

    /**
     * 更新@ManyMany关�?�表中的数�?�
     * 
     * @param regex
     *            关�?�字段的匹�?正则表达�?,如果为null则代表全部
     * @param chain
     *            键值对
     * @param cnd
     *            �?�件语�?�
     */
    public int updateRelation(String regex, Chain chain, Condition cnd) {
        return dao().updateRelation(getEntityClass(), regex, chain, cnd);
    }

    /**
     * 根�?��?�?�主键删除记录
     * 
     * @param pks
     *            �?�?�主键,必须按@Pk的声明顺�?传入
     * @return 删除的记录数
     */
    public int deletex(Object... pks) {
        return dao().deletex(getEntityClass(), pks);
    }

    /**
     * 根�?�一个实体的�?置信�?�为其创建一张表
     * 
     * @param dropIfExists
     *            如果表存在是�?�强制移除
     * @return 实体对象
     */
    public Entity<T> create(boolean dropIfExists) {
        return dao().create(getEntityClass(), dropIfExists);
    }

    /**
     * 与 insert(String tableName, Chain chain) 一样，�?过，数�?�表�??，将�?�自 POJO 的数�?�表声明，请�?�看
     * '@Table' 注解的详细说明
     * 
     * @param chain
     *            数�?��??值链
     */
    public void insert(Chain chain) {
        dao().insert(getEntityClass(), chain);
    }

    /**
     * 查询一组对象。你�?�以为这次查询设定�?�件
     * 
     * @param cnd
     *            WHERE �?�件。如果为 null，将获�?�全部数�?�，顺�?为数�?�库原生顺�?<br>
     *            �?�有在调用这个函数的时候， cnd.limit �?会生效
     * @return 对象列表
     */
    public List<T> query(Condition cnd) {
        return dao().query(getEntityClass(), cnd);
    }

    /**
     * 对一组对象进行迭代，这个接�?�函数�?�常适用于很大的数�?��?的集�?�，因为你�?�?�能把他们都读到内存里
     * 
     * @param cnd
     *            WHERE �?�件。如果为 null，将获�?�全部数�?�，顺�?为数�?�库原生顺�?
     * @param callback
     *            处�?�回调
     * @return 一共迭代的数�?
     */
    public int each(Condition cnd, Each<T> callback) {
        return dao().each(getEntityClass(), cnd, callback);
    }

    /**
     * 对�?一个对象字段，进行计算。
     * 
     * @param funcName
     *            计算函数�??，请确�?你的数�?�库是支�?这个函数的
     * @param fieldName
     *            对象 java 字段�??
     * @return 计算结果
     */
    public int func(String funcName, String fieldName) {
        return dao().func(getEntityClass(), funcName, fieldName);
    }

    /**
     * 对�?一个对象字段，进行计算。
     * 
     * @param funcName
     *            计算函数�??，请确�?你的数�?�库是支�?这个函数的
     * @param fieldName
     *            对象 java 字段�??
     * @param cnd
     *            过滤�?�件
     * @return 计算结果
     */
    public int func(String funcName, String fieldName, Condition cnd) {
        return dao().func(getEntityClass(), funcName, fieldName, cnd);
    }

    /**
     * 从一个 ResultSet 中获�?�一个对象。
     * <p>
     * 因为 Dao 接�?��?�以知�?�一个 POJO 的映射细节，这个函数�?�以帮你节�?一点体力。
     * 
     * @param rs
     *            结果集
     * @param fm
     *            字段过滤器
     * @return 对象
     */
    public T getObject(ResultSet rs, FieldMatcher fm) {
        return dao().getObject(getEntityClass(), rs, fm);
    }
    
    public T getObject(ResultSet rs, FieldMatcher fm, String prefix) {
        return dao().getObject(getEntityClass(), rs, fm, prefix);
    }
    
    public List<T> _query(final Condition cnd, final Pager pager, FieldMatcher matcher) {
        return dao().query(getEntityClass(), cnd, pager, matcher);
    }
    
    public List<T> _query(final Condition cnd, final Pager pager, String regex) {
        return dao().query(getEntityClass(), cnd, pager, regex);
    }
    
    public T _insert(T obj){
        return dao().insert(obj);
    }
    
    public T _fastInsert(T obj){
        return dao().fastInsert(obj);
    }
    
    public T _insert(T obj, FieldFilter filter){
        return dao().insert(obj,filter);
    }
    
    public T _insert(T t, boolean ignoreNull, boolean ignoreZero, boolean ignoreBlankStr){
        return dao().insert(t,ignoreNull,ignoreZero,ignoreBlankStr);
    }
    
    public T _insertWith(T obj, String regex){
        return dao().insertWith(obj,regex);
    }
    
    public T _insertLinks(T obj, String regex){
        return dao().insertLinks(obj,regex);
    }
    
    public T _insertRelation(T obj, String regex){
        return dao().insertRelation(obj,regex);
    }
    
    public int _update(T obj){
        return dao().update(obj);
    }
    
    public int _update(T obj, String regex){
        return dao().update(obj,regex);
    }
    
    public int _updateIgnoreNull(Object obj){
        return dao().updateIgnoreNull(obj);
    }
    
    public T _updateWith(T obj, String regex){
        return dao().updateWith(obj,regex);
    }
    
    public T _updateLinks(T obj, String regex){
        return dao().updateLinks(obj,regex);
    }
    
    public int _delete(T obj){
        return dao().delete(obj);
    }
    
    public int _deleteWith(T obj, String regex){
        return dao().deleteWith(obj,regex);
    }
    
    public int _deleteLinks(T obj, String regex){
        return dao().deleteLinks(obj,regex);
    }
    
    public T _fetch(T obj){
        return dao().fetch(obj);
    }
    
    public T _fetchLinks(T obj, String regex){
        return dao().fetchLinks(obj,regex);
    }
    
    public T _fetchLinks(T obj, String regex, Condition cnd){
        return dao().fetchLinks(obj,regex,cnd);
    }
    
    public T _clearLinks(T obj, String regex){
        return dao().clearLinks(obj,regex);
    }
    
    public void setExpert(T obj) throws Exception{
         dao().setExpert(obj);
    }
    
    public List<T> query() {
        return dao().query(getEntityClass(),null);
    }
}
