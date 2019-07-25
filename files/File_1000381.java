package org.nutz.dao.impl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.EntityMaker;
import org.nutz.dao.impl.entity.MapEntityMaker;
import org.nutz.dao.jdbc.JdbcExpert;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;

/**
 * �?装一些获�?�实体对象的帮助函数
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class EntityHolder {

    // DaoSupport 会设置这个值
    public EntityMaker maker;

    protected JdbcExpert expert;

    private Map<Class<?>, Entity<?>> map;
    
    protected DataSource dataSource;
    
    protected MapEntityMaker mapEntityMaker;

    public EntityHolder(JdbcExpert expert, DataSource dataSource) {
        this.expert = expert;
        this.dataSource = dataSource;
        this.map = new ConcurrentHashMap<Class<?>, Entity<?>>();
        mapEntityMaker = new MapEntityMaker();
        mapEntityMaker.init(dataSource, expert, this);
    }

    public void set(Entity<?> en) {
        synchronized (map) {
            this.map.put(en.getType(), en);
        }
    }

    public void remove(Entity<?> en) {
        if (en == null || en.getType() == null)
            return;
        synchronized (map) {
            this.map.remove(en.getType());
        }
    }

    /**
     * 根�?�类型获�?�实体
     * 
     * @param classOfT
     *            实体类型
     * @return 实体
     */
    @SuppressWarnings("unchecked")
    public <T> Entity<T> getEntity(Class<T> classOfT) {
        Entity<?> re = map.get(classOfT);
        if (null == re || !re.isComplete()) {
            synchronized (map) {
                re = map.get(classOfT);
                if (null == re) {
                    re = maker.make(classOfT);
                }
            }
        }
        return (Entity<T>) re;
    }

    public <T extends Map<String, ?>> Entity<T> makeEntity(String tableName, T map) {
        return mapEntityMaker.make(tableName, map);
    }

    /**
     * 根�?�一个对象获�?�实体
     * <p>
     * 对象如果是集�?�或者数组，则�?�其第一个元素进行判断
     * 
     * @param obj
     *            对象
     * @return 实体
     */
    @SuppressWarnings("unchecked")
    public Entity<?> getEntityBy(Object obj) {
        // 正常的构建一个 Entity
        Object first = Lang.first(obj);
        // 对象为空，�?能构建实体
        if (first == null)
            return null;

        // 这是一个 Map,试图构建一个 entity
        if (first instanceof Map<?, ?>) {
            Object tableName = ((Map<String, ?>) first).get(".table");
            if (null == tableName)
                throw Lang.makeThrow("Can not insert map without key '.table' : \n%s",
                                     Json.toJson(first, JsonFormat.forLook()));
            return makeEntity(tableName.toString(), (Map<String, ?>) first);
        }
        // 作为 POJO 构建
        return getEntity(first.getClass());
    }

    public boolean hasType(Class<?> typeName) {
        synchronized (map) {
            return map.containsKey(typeName);
        }
    }

    public void clear() {
        map.clear();
    }

    public void remove(String className) {
        Set<Class<?>> keys = new HashSet<Class<?>>(map.keySet());
        for (Class<?> klass : keys) {
            if (klass.getName().equals(className))
                map.remove(klass);
        }
    }
}
