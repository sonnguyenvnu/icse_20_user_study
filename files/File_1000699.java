package org.nutz.lang.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.nutz.castor.Castors;
import org.nutz.el.El;
import org.nutz.lang.Each;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.lang.born.Borning;

/**
 * 对于 LinkedHashMap 的一个�?�好�?装
 * <p>
 * �?� TreeMap �?�?�的是，如果 get(null)，它�?会抛错，就是返回 null 或默认值
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class NutMap extends LinkedHashMap<String, Object> implements NutBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static NutMap WRAP(Map<String, Object> map) {
        if (null == map)
            return null;
        if (map instanceof NutMap)
            return (NutMap) map;
        return new NutMap(map);
    }

    public NutMap() {
        super();
    }

    public NutMap(Map<String, Object> map) {
        super();
        this.putAll(map);
    }

    public NutMap(String json) {
        super();
        this.putAll(Lang.map(json));
    }

    public NutMap(String key, Object value) {
        super();
        put(key, value);
    }

    /**
     * 设置一个字段，如果值为 null 则表示移除
     * 
     * @param key
     *            键
     * @param v
     *            值
     */
    @Override
    public void setOrRemove(String key, Object v) {
        if (null == v) {
            this.remove(key);
        } else {
            this.put(key, v);
        }
    }

    public static NutMap NEW() {
        return new NutMap();
    }

    public static NutMap WRAP(String json) {
        return new NutMap(json);
    }

    @Override
    public boolean has(String key) {
        return null != get(key);
    }

    @Override
    public boolean is(String key, Object val) {
        Object obj = this.get(key);
        if (null == obj && null == val)
            return true;
        if (null == obj || null == val)
            return false;
        return obj.equals(val);
    }

    public NutMap duplicate() {
        NutMap map = new NutMap();
        map.putAll(this);
        return map;
    }

    /**
     * 从 Map 里挑选一些键生�?一个新的 Map
     * 
     * @param keys
     *            键
     * @return 新 Map
     */
    @Override
    public NutMap pick(String... keys) {
        if (keys.length == 0)
            return new NutMap();
        NutMap re = new NutMap();
        for (String key : keys) {
            Object val = this.get(key);
            if (null != val)
                re.put(key, val);
        }
        return re;
    }

    /**
     * 从 Map 里挑选一些键生�?一个新的 Map，自己�?�时删除这些键
     * 
     * @param keys
     *            键
     * @return 新 Map
     */
    @Override
    public NutMap pickAndRemove(String... keys) {
        if (keys.length == 0)
            return new NutMap();
        NutMap re = new NutMap();
        for (String key : keys) {
            Object val = this.remove(key);
            re.put(key, val);
        }
        return re;
    }

    /**
     * 从 Map 里挑选一些键生�?一个新的 Map
     * 
     * @param regex
     *            匹�?键的正则表达�?，"!" 开头，表示�?��??
     * @return 新 Map
     */
    @Override
    public NutMap pickBy(String regex) {
        if (Strings.isBlank(regex))
            return this.duplicate();
        boolean isNot = regex.startsWith("!");
        Pattern p = Regex.getPattern(isNot ? regex.substring(1) : regex);
        return pickBy(p, isNot);
    }

    /**
     * 从 Map 里挑选一些键生�?一个新的 Map
     * 
     * @param p
     *            匹�?键的正则表达�?，null �?会匹�?任何一个键
     * @param isNot
     *            true 表示被匹�?上的会被忽略，false 表示被匹�?上的�?加入到返回的集�?�里
     * @return 新 Map
     */
    @Override
    public NutMap pickBy(Pattern p, boolean isNot) {
        // 一定�?匹�?
        if (null == p) {
            return isNot ? this.duplicate() : new NutMap();
        }

        // 挑选
        NutMap re = new NutMap();
        for (Map.Entry<String, Object> en : this.entrySet()) {
            String key = en.getKey();
            boolean matched = p.matcher(key).find();
            if (matched) {
                if (!isNot) {
                    re.put(key, en.getValue());
                }
            } else if (isNot) {
                re.put(key, en.getValue());
            }
        }

        // 返回
        return re;
    }

    /**
     * 就是 pickAndRemoveBy 的一个便利写法
     * 
     * @param regex
     *            正则表达�?，! 开头表示�?��??
     * @return 新 Map
     * 
     * @see #pickAndRemoveBy(Pattern, boolean)
     */
    public NutMap pickAndRemoveBy(String regex) {
        if (Strings.isBlank(regex))
            return new NutMap();
        boolean isNot = regex.startsWith("!");
        Pattern p = Pattern.compile(isNot ? regex.substring(1) : regex);
        return pickAndRemoveBy(p, isNot);
    }

    /**
     * 从 Map 里挑选一些键生�?一个新的 Map，自己�?�时删除这些键
     * 
     * @param p
     *            匹�?键的正则表达�?，null �?会匹�?任何一个键
     * @param isNot
     *            true 表示被匹�?上的会被忽略，false 表示被匹�?上的�?加入到返回的集�?�里
     * @return 新 Map
     */
    @Override
    public NutMap pickAndRemoveBy(Pattern p, boolean isNot) {
        // 一定�?匹�?
        if (null == p) {
            if (isNot) {
                NutMap re = this.duplicate();
                this.clear();
                return re;
            } else {
                return new NutMap();
            }
        }

        // 挑选
        NutMap re = new NutMap();
        List<String> delKeys = new ArrayList<String>(this.size());
        for (Map.Entry<String, Object> en : this.entrySet()) {
            String key = en.getKey();
            boolean matched = p.matcher(key).find();
            if (matched) {
                if (!isNot) {
                    delKeys.add(key);
                    re.put(key, en.getValue());
                }
            } else if (isNot) {
                delKeys.add(key);
                re.put(key, en.getValue());
            }
        }

        // 删除 Key
        for (String key : delKeys)
            this.remove(key);

        // 返回
        return re;
    }

    /**
     * 从 Map 里将指定的键过滤，生�?一个新的 Map
     * 
     * @param keys
     *            键
     * @return 新 Map
     */
    @Override
    public NutMap omit(String... keys) {
        NutMap re = new NutMap();
        for (Map.Entry<String, Object> en : this.entrySet()) {
            String key = en.getKey();
            if (!Lang.contains(keys, key)) {
                re.put(key, en.getValue());
            }
        }
        return re;
    }

    /**
     * 如果一个键的值无效（has(key) 返回 false)，那么为其设置默认值
     * 
     * @param key
     *            键
     * @param dft
     *            值
     * @return 自身以便链�?赋值
     */
    @Override
    public NutMap putDefault(String key, Object dft) {
        if (!this.has(key)) {
            this.put(key, dft);
        }
        return this;
    }

    @Override
    public boolean containsValue(Object value) {
        if (null == _map)
            return super.containsValue(value);
        return super.containsValue(value) || _map.containsValue(value);
    }

    @Override
    public boolean containsKey(Object key) {
        if (null == _map)
            return super.containsKey(key);
        return super.containsKey(key) || _map.containsKey(key);
    }

    @Override
    public Set<String> keySet() {
        if (null == _map)
            return super.keySet();
        HashSet<String> keys = new HashSet<String>();
        keys.addAll(super.keySet());
        keys.addAll(_map.keySet());
        return keys;
    }

    @Override
    public Collection<Object> values() {
        if (null == _map)
            return super.values();
        List<Object> vals = new LinkedList<Object>();
        for (String key : this.keySet()) {
            vals.add(this.get(key));
        }
        return vals;
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        if (null == _map)
            return super.entrySet();
        HashSet<Entry<String, Object>> vals = new HashSet<Entry<String, Object>>();
        vals.addAll(_map.entrySet());
        vals.addAll(super.entrySet());
        return vals;
    }

    @Override
    public void clear() {
        super.clear();
        if (null != _map)
            _map.clear();
    }

    private NutMap _map;

    public NutMap attach(NutMap map) {
        _map = map;
        return this;
    }

    public NutMap detach() {
        NutMap re = _map;
        _map = null;
        return re;
    }

    @Override
    public Object get(Object key) {
        if (_map == null)
            return super.get(key);

        if (super.containsKey(key)) {
            return super.get(key);
        }

        return _map.get(key);
    }

    @Override
    public Object get(String key, Object dft) {
        Object v = get(key);
        return null == v ? dft : v;
    }

    @Override
    public int getInt(String key) {
        return getInt(key, -1);
    }

    @Override
    public int getInt(String key, int dft) {
        Object v = get(key);
        return null == v ? dft : Castors.me().castTo(v, int.class);
    }

    @Override
    public float getFloat(String key) {
        return getFloat(key, Float.NaN);
    }

    @Override
    public float getFloat(String key, float dft) {
        Object v = get(key);
        return null == v ? dft : Castors.me().castTo(v, float.class);
    }

    @Override
    public long getLong(String key) {
        return getLong(key, -1);
    }

    @Override
    public long getLong(String key, long dft) {
        Object v = get(key);
        return null == v ? dft : Castors.me().castTo(v, long.class);
    }

    @Override
    public double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    @Override
    public double getDouble(String key, double dft) {
        Object v = get(key);
        return null == v ? dft : Castors.me().castTo(v, double.class);
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(String key, boolean dft) {
        Object v = get(key);
        return null == v ? dft : Castors.me().castTo(v, boolean.class);
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public String getString(String key, String dft) {
        Object v = get(key);
        if (v == null)
            return dft;
        if (v instanceof CharSequence)
            return v.toString();
        if (v instanceof List) {
            v = ((List) v).iterator().next();
        }
        // by wendal : 这还有必�?castTo么?
        // zozoh: 当然有啦，比如日期对象，�?�?��?字符串的�? ...
        return Castors.me().castTo(v, String.class);
    }

    @Override
    public Date getTime(String key) {
        return getTime(key, null);
    }

    @Override
    public Date getTime(String key, Date dft) {
        Object v = get(key);
        return null == v ? dft : Castors.me().castTo(v, Date.class);
    }

    @Override
    public <T extends Enum<T>> T getEnum(String key, Class<T> classOfEnum) {
        String s = getString(key);
        if (Strings.isBlank(s))
            return null;
        return Enum.valueOf(classOfEnum, s);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isEnum(String key, Enum<?>... eus) {
        if (null == eus || eus.length == 0)
            return false;
        try {
            Enum<?> v = getEnum(key, eus[0].getClass());
            for (Enum<?> eu : eus)
                if (!v.equals(eu))
                    return false;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public <T> T getAs(String key, Class<T> classOfT) {
        return getAs(key, classOfT, null);
    }

    @Override
    public <T> T getAs(String key, Class<T> classOfT, T dft) {
        Object v = get(key);
        return null == v ? dft : Castors.me().castTo(v, classOfT);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T> List<T> getAsList(String key, Class<T> eleType) {
        Object v = get(key);
        if (null == v)
            return null;
        List list = (List) v;
        ListIterator it = list.listIterator();
        while (it.hasNext()) {
            Object ele = it.next();
            if (null != ele && !eleType.isAssignableFrom(ele.getClass())) {
                Object ele2 = Castors.me().castTo(ele, eleType);
                it.set(ele2);
            }
        }
        return list;
    }

    /**
     * @see #getList(String, Class, List)
     */
    @Override
    public <T> List<T> getList(String key, final Class<T> eleType) {
        return getList(key, eleType, new ArrayList<T>());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key, final Class<T> eleType, List<T> dft) {
        Object v = get(key);
        if (null == v)
            return dft;

        if (v instanceof CharSequence) {
            return Lang.list(Castors.me().castTo(v, eleType));
        }

        int len = Lang.eleSize(v);
        final List<T> list = new ArrayList<T>(len);
        Lang.each(v, new Each<Object>() {
            @Override
            public void invoke(int index, Object ele, int length) {
                list.add(Castors.me().castTo(ele, eleType));
            }
        });

        return list;

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] getArray(String key, Class<T> eleType) {
        return getArray(key, eleType, (T[]) Array.newInstance(eleType, 0));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] getArray(String key, final Class<T> eleType, T[] dft) {
        Object v = get(key);
        if (null == v)
            return dft;

        if (v instanceof CharSequence) {
            return Lang.array(Castors.me().castTo(v, eleType));
        }

        int len = Lang.eleSize(v);
        final Object arr = Array.newInstance(eleType, len);
        final int[] i = new int[]{0};
        Lang.each(v, new Each<Object>() {
            @Override
            public void invoke(int index, Object ele, int length) {
                Array.set(arr, i[0]++, Castors.me().castTo(ele, eleType));
            }
        });

        return (T[]) arr;

    }

    /**
     * 为 Map 增加一个�??值对。如果�?��??已�?有值了，那么会将两个值�?�并�?一个列表
     * 
     * @param key
     * @param value
     */
    @Override
    @SuppressWarnings("unchecked")
    public NutMap addv(String key, Object value) {
        Object obj = get(key);
        if (null == obj) {
            put(key, value);
        } else if (obj instanceof List<?>)
            ((List<Object>) obj).add(value);
        else {
            List<Object> list = new LinkedList<Object>();
            list.add(obj);
            list.add(value);
            put(key, list);
        }
        return this;
    }

    /**
     * 为 Map 增加一个�??值对。强制设置为一个列表，如果有�?��??则�?�并
     * 
     * @param key
     * @param value
     */
    @Override
    @SuppressWarnings("unchecked")
    public NutMap addv2(String key, Object value) {
        List<Object> list = (List<Object>) get(key);
        if (null == list) {
            list = new LinkedList<Object>();
            put(key, list);
        }
        list.add(value);
        return this;
    }

    /**
     * �?��?个键增加一组值，如果原�?�就有值，是集�?�的�?，会被�?�并，�?�则原�?�的值用列表包裹�?��?加入新值
     * 
     * @param key
     *            键
     * @param values
     *            值列表
     * @return 自身
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T> NutMap pushTo(String key, T... values) {
        if (null != values && values.length > 0) {
            Object v = get(key);
            // �?存在的�?，增加列表
            if (null == v) {
                List<Object> list = new LinkedList<Object>();
                for (Object val : values)
                    list.add(val);
                this.put(key, list);
            }
            // 如果是集�?�的�?，就增加
            else if (v instanceof Collection) {
                for (Object val : values)
                    ((Collection) v).add(val);
            }
            // �?�则将原�?�的值�?��?列表�?增加
            else {
                List<Object> list = new LinkedList<Object>();
                list.add(v);
                for (Object val : values)
                    list.add(val);
                this.put(key, list);
            }
        }
        // 返回自身以便链�?赋值
        return this;
    }

    /**
     * 是 pushTo 函数的�?�一个�?��?（�?�以接�?�集�?�）
     * 
     * @param key
     *            键
     * @param values
     *            值列表
     * @return 自身
     * 
     * @see #pushTo(String, Collection)
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public NutMap pushTo(String key, Collection<?> values) {
        if (null != values && values.size() > 0) {
            Object v = get(key);
            // �?存在的�?，增加列表
            if (null == v) {
                List<Object> list = new LinkedList<Object>();
                list.addAll(values);
                this.put(key, list);
            }
            // 如果是集�?�的�?，就增加
            else if (v instanceof Collection) {
                ((Collection) v).addAll(values);
            }
            // �?�则将原�?�的值�?��?列表�?增加
            else {
                List<Object> list = new LinkedList<Object>();
                list.add(v);
                list.addAll(values);
                this.put(key, list);
            }
        }
        // 返回自身以便链�?赋值
        return this;
    }

    /**
     * @deprecated 本函数�?义容易�?�生混淆，已�?改�??�? addv，下个版将被删除
     * @since 1.b.51
     */
    @Deprecated
    public NutMap putv(String key, Object value) {
        return addv(key, value);
    }

    @Override
    public NutMap setv(String key, Object value) {
        this.put(key, value);
        return this;
    }

    @Override
    public void unset(String key) {
        this.remove(key);
    }

    @Override
    public NutBean setAll(Map<String, Object> map) {
        this.putAll(map);
        return this;
    }

    @Override
    public NutMap setMap(Map<?, ?> map, boolean ignoreNullValue) {
        for (Map.Entry<?, ?> en : map.entrySet()) {
            Object key = en.getKey();
            Object val = en.getValue();

            if (null == key)
                continue;

            if (null == val && ignoreNullValue)
                continue;

            this.put(key.toString(), val);
        }
        return this;
    }

    /**
     * 相当于 mergeWith(map, false)
     * 
     * @see #mergeWith(Map, boolean)
     */
    @Override
    public NutMap mergeWith(Map<String, Object> map) {
        return this.mergeWith(map, false);
    }

    /**
     * 与一个给定的 Map �?�?�，如果有�? Map 递归
     * 
     * @param map
     *            �?�?�并进�?�的 Map
     * @param onlyAbsent
     *            true 表示�?�有没有 key �?设置值
     * @return 自身以便链�?赋值
     */
    @Override
    @SuppressWarnings("unchecked")
    public NutMap mergeWith(Map<String, Object> map, boolean onlyAbsent) {
        for (Map.Entry<String, Object> en : map.entrySet()) {
            String key = en.getKey();
            Object val = en.getValue();

            if (null == key || null == val)
                continue;

            Object myVal = this.get(key);

            // 如果两边都是 Map ，则�?�?�
            if (null != myVal && myVal instanceof Map && val instanceof Map) {
                Map<String, Object> m0 = (Map<String, Object>) myVal;
                Map<String, Object> m1 = (Map<String, Object>) val;
                NutMap m2 = NutMap.WRAP(m0).mergeWith(m1, onlyAbsent);
                // �?�出了新 Map，设置一下
                if (m2 != m0)
                    this.put(key, m2);
            }
            // �?�有没有的时候�?设置
            else if (onlyAbsent) {
                this.setnx(key, val);
            }
            // �?�则直接替�?�
            else {
                this.put(key, val);
            }
        }

        return this;
    }

    /**
     * 与JDK8+的 putIfAbsent(key, val)一致, 当且仅当值�?存在时设置进去,但与putIfAbsent返回值有�?一样
     * 
     * @param key
     *            键
     * @param val
     *            值
     * @return 当�?的NutMap实例
     */
    @Override
    public NutMap setnx(String key, Object val) {
        if (!containsKey(key))
            setv(key, val);
        return this;
    }

    /**
     * 将一个集�?�与自己补充（相当于针对�?个 key 调用 setnx)
     * 
     * @param map
     *            集�?�
     * @return 自身
     * 
     * @see #setnx(String, Object)
     */
    @Override
    public NutMap setnxAll(Map<String, Object> map) {
        if (null != map && map.size() > 0) {
            for (Map.Entry<String, Object> en : map.entrySet()) {
                this.setnx(en.getKey(), en.getValue());
            }
        }
        return this;
    }

    /**
     * 获�?�对应的值,若�?存在,用factory创建一个,然�?�设置进去,返回之
     * 
     * @param key
     *            键
     * @param factory
     *            若�?存在的�?用于生�?实例
     * @return 已存在的值或新的值
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getOrBorn(String key, Borning<T> factory) {
        T t = (T) get(key);
        if (t == null) {
            t = factory.born(key);
            put(key, t);
        }
        return t;
    }

    /**
     * 将自身作为一个�?�件，看看给定的 Map 是�?�全部满足这个�?�件
     * <p>
     * 注�?，字符串型的值有下列�?义
     * <ul>
     * <li>"^xxxxx" : 正则表达�?
     * <li>"" : 相当于 Blank
     * </ul>
     * 
     * @param map
     *            给定的 Map
     * @return 是�?�匹�?
     */
    @Override
    public boolean match(Map<String, Object> map) {
        // 空 map 一定是�?匹�?的
        if (null == map)
            return false;

        // 本 Map 如果没值，表示全匹�?
        if (this.size() == 0)
            return true;

        // �?个匹�?键
        for (Map.Entry<String, Object> en : this.entrySet()) {
            String key = en.getKey();
            Object mtc = en.getValue();

            // null 表示对方�?能包括这个键
            if (null == mtc) {
                if (map.containsKey(key))
                    return false;
            }
            // 其他的值，匹�?一下
            else {
                Object val = map.get(key);
                if (!__match_val(mtc, val)) {
                    return false;
                }
            }
        }
        // 都检查过了 ...
        return true;
    }

    private boolean __match_val(final Object mtc, Object val) {
        Mirror<?> mi = Mirror.me(mtc);

        // 如果为 null，则�?�有空串能匹�?
        if (null == val) {
            return mi.isStringLike() && Strings.isEmpty(mtc.toString());
        }

        // 字符串的�?
        Pattern regex = mi.is(Pattern.class) ? (Pattern) mtc : null;
        if (mi.isStringLike()) {

            final String s = mtc.toString();
            if (s.startsWith("^")) {
                regex = Regex.getPattern(s);
            }
            // �?是正则表达�?，那么精确匹�?字符串
            else {
                final boolean[] re = new boolean[1];
                Lang.each(val, new Each<Object>() {
                    @Override
                    public void invoke(int index, Object ele, int length) {
                        if (null != ele && ele.equals(s)) {
                            re[0] = true;
                            Lang.Break();
                        }
                    }
                });
                return re[0];
            }
        }

        // 正则表达�?
        if (null != regex) {
            final boolean[] re = new boolean[1];
            final Pattern REG = regex;
            Lang.each(val, new Each<Object>() {
                @Override
                public void invoke(int index, Object ele, int length) {
                    if (null != ele && REG.matcher(ele.toString()).matches()) {
                        re[0] = true;
                        Lang.Break();
                    }
                }
            });
            return re[0];
        }

        // 简�?�类型的比较
        if (mi.isSimple()) {
            final boolean[] re = new boolean[1];
            Lang.each(val, new Each<Object>() {
                @Override
                public void invoke(int index, Object ele, int length) {
                    if (null != ele && ele.equals(mtc)) {
                        re[0] = true;
                        Lang.Break();
                    }
                }
            });
            return re[0];
        }
        // 范围的�?...
        else if (mi.is(Region.class)) {
            throw Lang.noImplement();
        }
        // 其他的统统为�?匹�?
        return false;
    }

    public Object eval(String el) {
        return El.eval(Lang.context(this), el);
    }

    public int evalInt(String el) {
        Object obj = El.eval(Lang.context(this), el);
        if (obj == null)
            return 0;
        if (obj instanceof Number)
            return ((Number) obj).intValue();
        return Integer.parseInt(obj.toString());
    }

    /**
     * 指定key进行自增�?作，并返回结果
     * 
     * @param key
     *            键
     * @return 自增�?�结果
     */
    public int intIncrement(String key) {
        return intIncrement(key, 1);
    }

    /**
     * 指定key进行增�?作，并返回结果
     * 
     * @param key
     *            键
     * @param number
     *            数值
     * @return 增�?�结果
     */
    public int intIncrement(String key, int number) {
        int val = getInt(key, 0);
        val += number;
        setv(key, val);
        return val;
    }

    /**
     * 指定key进行自�?�?作，并返回结果
     * 
     * @param key
     *            键
     * @return 自�?�?�结果
     */
    public int intDecrement(String key) {
        return intDecrement(key, 1);
    }

    /**
     * 指定key进行�?�?作，并返回结果
     * 
     * @param key
     *            键
     * @param number
     *            数值
     * @return �?�?�结果
     */
    public int intDecrement(String key, int number) {
        int val = getInt(key, 0);
        val -= number;
        setv(key, val);
        return val;
    }

    /**
     * 指定key进行自增�?作，并返回结果
     * 
     * @param key
     *            键
     * @return 自增�?�结果
     */
    public long longIncrement(String key) {
        return longIncrement(key, 1);
    }

    /**
     * 指定key进行增�?作，并返回结果
     * 
     * @param key
     *            键
     * @param number
     *            数值
     * @return 增�?�结果
     */
    public long longIncrement(String key, long number) {
        long val = getLong(key, 0);
        val += number;
        setv(key, val);
        return val;
    }

    /**
     * 指定key进行自�?�?作，并返回结果
     * 
     * @param key
     *            键
     * @return 自�?�?�结果
     */
    public long longDecrement(String key) {
        return longDecrement(key, 1);
    }

    /**
     * 指定key进行�?�?作，并返回结果
     * 
     * @param key
     *            键
     * @param number
     *            数值
     * @return �?�?�结果
     */
    public long longDecrement(String key, long number) {
        long val = getLong(key, 0);
        val -= number;
        setv(key, val);
        return val;
    }
}
