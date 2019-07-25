package org.nutz.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.MappingField;
import org.nutz.dao.jdbc.ValueAdaptor;
import org.nutz.dao.util.Daos;
import org.nutz.json.Json;
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.lang.util.Callback2;
import org.nutz.lang.util.NutMap;

/**
 * �??值链。
 * <p>
 * 通过 add 方法，建立一�?��??值对的链表
 * 
 * @author zozoh(zozohtnt@gmail.com)
 * @author Wendal(wendal1985@gmail.com)
 * @author lzxz1234
 */
public abstract class Chain implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 建立一�?��??值链开始的一环
     * 
     * @param name
     *            �??称
     * @param value
     *            值
     * @return 链头
     */
    public static Chain make(String name, Object value) {
        return new DefaultChain(name, value);
    }

    /**
     * @return 链的长度
     */
    public abstract int size();

    /**
     * 改�?�当�?节点的�??称
     * 
     * @param name
     *            新�??称
     * @return 当�?节点
     */
    public abstract Chain name(String name);

    /**
     * 改�?�当�?节点的值
     * 
     * @param value
     *            新值
     * @return 当�?节点
     */
    public abstract Chain value(Object value);

    /**
     * 设置节点的�?�考适�?器
     * 
     * @param adaptor
     *            适�?器
     * @return 当�?节点
     */
    public abstract Chain adaptor(ValueAdaptor adaptor);

    /**
     * @return 当�?节点的�?�考适�?器
     */
    public abstract ValueAdaptor adaptor();

    /**
     * 将一个�??值对，添加为本链节点的下一环
     * 
     * @param name
     *            �??
     * @param value
     *            值
     * @return 新增加的节点
     */
    public abstract Chain add(String name, Object value);
    
    /**
     * @return 当�?节点的�??称
     */
    public abstract String name();

    /**
     * @return 当�?节点的值
     */
    public abstract Object value();

    /**
     * @return 往�?�移动一个结点，到达末尾返回空，�?�则返回当�?对象
     */
    public abstract Chain next();

    /**
     * @return 整个链的第一环（头节点）
     */
    public abstract Chain head();

    /**
     * 根�?� Entity 里的设定，更新整个链所有节点的�??称。
     * <p>
     * 如果节点的�??称是 Entity 的一个字段，则采用数�?�库字段的�??称
     * 
     * @param entity
     *            实体
     * @return 链头节点
     */
    public abstract Chain updateBy(Entity<?> entity);

    /**
     * 由当�?的�??值链，生�?一个对象
     * 
     * @param classOfT
     *            对象类型
     * @return 对象实例
     */
    public abstract <T> T toObject(Class<T> classOfT);

    /**
     * 由当�?�??值链，生�?一个 Map
     * 
     * @return Map
     */
    public abstract Map<String, Object> toMap();


    /**
     * 整个Chain是�?�为特殊Chain，�?��?有一个特殊结点，就是特殊Chain
     * @see org.nutz.dao.Chain#addSpecial(String, Object)
     * @since 1.b.44
     */
    public abstract boolean isSpecial();
    
    /** 
     * 当�?结点是�?是特殊结点
     * @return 是�?是特殊结点
     */
    public abstract boolean special();
    
    /**
     * 由当�?的值链生�?一个�?�被实体化的 Map。 �?�有 '.table' 属性
     * 
     * @param tableName
     *            表�??
     * @return �?�被实体化的 Map
     */
    public Map<String, Object> toEntityMap(String tableName) {
        Map<String, Object> map = toMap();
        map.put(".table", tableName);
        return map;
    }

    /**
     * 生�?一个 JSON 字符串
     */
    public String toString() {
        return Json.toJson(toMap());
    }

    /**
     * 根�?�一个对象的字段 生�?一个 Chain 对象
     * <p>
     * 这个对象�?�以是一个 POJO 或者是一个 Map。
     * <p>
     * 支�? FieldMatcher，�?�你�?�以通过 FieldMatcher �?�指定你需�?哪些字段加入 Chain
     * 
     * @param obj
     *            对象，�?�以是一个 POJO 或者是一个 Map
     * @param fm
     *            指明�?�用字段，null 表示全部字段�?�用
     * @return Chain 对象，null 表示对象中没有�?�用字段
     * 
     * @see org.nutz.dao.FieldMatcher
     */
    public static Chain from(Object obj, FieldMatcher fm) {
        if (null == obj)
            return null;
        Chain c = null;
        /*
         * Is Map
         */
        if (obj instanceof Map<?, ?>) {
            for (Map.Entry<?, ?> en : ((Map<?, ?>) obj).entrySet()) {
                Object key = en.getKey();
                if (null == key)
                    continue;
                String name = key.toString();
                if (null != fm && !fm.match(name))
                    continue;
                Object v = en.getValue();
                if (null != fm ) {
                    if (null == v) {
                        if (fm.isIgnoreNull())
                            continue;
                    } else if (fm.isIgnoreBlankStr() && v instanceof String && Strings.isBlank((String)v)) {
                        continue;
                    }
                }
                if (c == null) {
                    c = Chain.make(name, v);
                } else {
                    c = c.add(name, v);
                }
            }
        }
        /*
         * Is POJO
         */
        else {
            Mirror<?> mirror = Mirror.me(obj.getClass());
            for (Field f : mirror.getFields()) {
                if (null != fm && !fm.match(f.getName()))
                    continue;
                Object v = mirror.getValue(obj, f.getName());
                if (null == v) {
                    if (fm != null && fm.isIgnoreNull())
                        continue;
                } else if (fm != null && fm.isIgnoreBlankStr() && v instanceof String && Strings.isBlank((String)v)) {
                    continue;
                }
                if (c == null) {
                    c = Chain.make(f.getName(), v);
                } else {
                    c = c.add(f.getName(), v);
                }
            }
        }
        return c;
    }

    /**
     * 根�?�一个 POJO 对象的字段 生�?一个 Chain 对象
     * <p>
     * 相当于 Chain.from(obj,null)
     * 
     * @param obj
     *            POJO 对象
     * @return Chain 对象
     */
    public static Chain from(Object obj) {
        return from(obj, null);
    }
    
    public static Chain from(Object obj, FieldMatcher fm, Dao dao) {
        final Chain[] chains = new Chain[1];
        boolean re = Daos.filterFields(obj, fm, dao, new Callback2<MappingField, Object>() {
            public void invoke(MappingField mf, Object val) {
                if (mf.isReadonly() || !mf.isUpdate())
                    return;
                if (chains[0] == null)
                    chains[0] = Chain.make(mf.getName(), val);
                else
                    chains[0].add(mf.getName(), val);
            }
        });
        if (re)
            return chains[0];
        return null;
    }
    
    //=============================================================
    //===========update语�?�使用特定的值,例如+1 -1 toDate()等========
    //=============================================================
    
    /**
     * 添加一个特殊节点, 如果value�?�空而且是String类型,则有3个情况:<p>
     * <li>+1 效果如age=age+1</li>
     * <li>-1 效果如count=count-1</li>
     * <li>支�?的�?算符有 + - *\/ % & ^ |
     * <li>其他值, 则对value.toString()</li>
     * <p/>
     * <code>Chain chain = Chain.makeSpecial("age", "+1");//输出的SQL会是 age=age+1</code>
     * <p/>
     * <code>Chain chain = Chain.makeSpecial("ct", "now()");//输出的SQL会是 ct=now(),但�?建议用�?赖特定数�?�库的now(),仅供演示.</code>
     * @since 1.b.44
     */
    public abstract Chain addSpecial(String name, Object value);
    
    /**
     * @see org.nutz.dao.Chain#addSpecial(String, Object)
     * @since 1.b.44
     */
    public static Chain makeSpecial(String name, Object value) {
        DefaultChain chain = new DefaultChain(name, value);
        chain.head.special = true;
        return chain;
    }
    
    public static class DefaultChain extends Chain implements Serializable {
        private static final long serialVersionUID = 1L;
        private ChainEntry head;
        private ChainEntry current;
        private ChainEntry tail;
        private int size;
        
        public DefaultChain(String name, Object value) {
            this.head = new ChainEntry(name, value);
            this.current = head;
            this.tail = head;
            this.size = 1;
        }
        public int size() {
            return size;
        }
        public Chain name(String name) {
            current.name = name;
            return this;
        }
        public Chain value(Object value) {
            current.value = value;
            return this;
        }
        public Chain adaptor(ValueAdaptor adaptor) {
            current.adaptor = adaptor;
            return this;
        }
        public ValueAdaptor adaptor() {
            return current.adaptor;
        }
        public Chain add(String name, Object value) {
            tail.next = new ChainEntry(name, value);
            tail = tail.next;
            size ++;
            return this;
        }
        public String name() {
            return current.name;
        }
        public Object value() {
            return current.value;
        }
        public Chain next() {
            current = current.next;
            return current == null ? null : this;
        }
        public Chain head() {
            current = head;
            return this;
        }
        public Chain addSpecial(String name, Object value) {
            add(name, value);
            tail.special = true;
            return this;
        }
        public boolean special() {
            return current.special;
        }
        public boolean isSpecial() {
            ChainEntry entry = head;
            do {
                if(entry.special) {
                    return true;
                }
            } while ((entry = entry.next) != null);
            return false;
        }
        public Map<String, Object> toMap() {
            NutMap map = new NutMap();
            ChainEntry current = head;
            while (current != null) {
                map.put(current.name, current.value);
                if (current.adaptor != null)
                	map.put("."+current.name+".adaptor", current.adaptor);
                current = current.next;
            }
            return map;
        }
        public Chain updateBy(Entity<?> entity) {
            if (null != entity) {
                ChainEntry current = head;
                while (current != null) {
                    MappingField ef = entity.getField(current.name);
                    if (null != ef) {
                        current.name = ef.getColumnNameInSql();
                    }
                    current = current.next;
                }
            }
            return head();
        }
        public <T> T toObject(Class<T> classOfT) {
            Mirror<T> mirror = Mirror.me(classOfT);
            T re = mirror.born();
            ChainEntry current = head;
            while (current != null) {
                mirror.setValue(re, current.name, current.value);
                current = current.next;
            }
            return re;
        }
    }
    
    public static class ChainEntry implements Serializable {
        private static final long serialVersionUID = 1L;
        protected String name;
        protected Object value;
        protected transient ValueAdaptor adaptor;
        protected boolean special;
        protected ChainEntry next;
        public ChainEntry(String name, Object value) {
            this.name = name;
            this.value = value;
        }
    }
}
