package org.nutz.mapl;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import org.nutz.mapl.impl.MaplMerge;
import org.nutz.mapl.impl.MaplRebuild;
import org.nutz.mapl.impl.compile.ObjCompileImpl;
import org.nutz.mapl.impl.convert.FilterConvertImpl;
import org.nutz.mapl.impl.convert.ObjConvertImpl;
import org.nutz.mapl.impl.convert.StructureConvert;

/**
 * 集�?�了对象转�?��?�并等高级�?作
 * 
 * @author juqkai(juqkai@gmail.com)
 * 
 */
public class Mapl {
    /**
     * 这个实现, 主�?将 List, Map 的对象结构转�?��?真实的对象.
     * <p>
     * 规则:
     * <ul>
     * <li>对象以Map存储, key为属性�??, value为属性值
     * <li>数组以List存储
     * <li>Map直接存储为Map
     * <li>List直接存储为List
     * <li>�?��?�?是List, Map 存储的, 都认为是�?�以直接写入对象的. TODO 这点�?�以调整一下.
     * </ul>
     */
    public static Object maplistToObj(Object maplist, Type type) {
        return new ObjConvertImpl(type).convert(maplist);
    }

    /**
     * 与maplistToObj实现一样, 少了一步强制转�?�而已
     * 
     */
    @SuppressWarnings("unchecked")
    public static <T> T maplistToT(Object maplist, Class<T> clz) {
        return (T) new ObjConvertImpl(clz).convert(maplist);
    }

    // ------------------------------------------------------------------
    /**
     * 访问MAP, List结构的数�?�, 通过 uers[2].name 这�?形�?.
     * 
     * @param maplist
     * @param path
     *            路径
     */
    public static Object cell(Object maplist, String path) {
        MaplRebuild mr = new MaplRebuild(maplist);
        return mr.cell(path);
    }

    // ------------------------------------------------------------------

    /**
     * 转�?�器中间对象�?�并器<br/>
     * �?�并 {@link Mapl} 中定义的中间结构.<br/>
     * 规则:<br>
     * <ul>
     * <li>普通对象, �?存为List, 但是�?去掉�?�?.
     * <li>�?�并 map , 如果 key 值相�?�, 那么�?�一个值覆盖�?�?�的值.递归�?�并
     * <li>list�?�?�递归�?�并, �?��?�简�?�的�?�并, 清除�?�?的�?作.
     * </ul>
     */
    public static Object merge(Object... maplists) {
        return MaplMerge.merge(maplists);
    }

    // ------------------------------------------------------------------
    /**
     * 包�?�MapList过滤器, 详情�?��?: {@link FilterConvertImpl}
     * 
     * @param maplist
     *            maplist结构的对象
     * @param paths
     *            过滤列表
     */
    public static Object includeFilter(Object maplist, List<String> paths) {
        FilterConvertImpl filter = new FilterConvertImpl(paths);
        filter.useIncludeModel();
        return filter.convert(maplist);
    }

    /**
     * 排除MapList过滤器, 详情�?��?: {@link FilterConvertImpl}
     * 
     * @param maplist
     *            maplist结构的对象
     * @param paths
     *            过滤列表
     */
    public static Object excludeFilter(Object maplist, List<String> paths) {
        FilterConvertImpl filter = new FilterConvertImpl(paths);
        filter.useExcludeModel();
        return filter.convert(maplist);
    }

    // ------------------------------------------------------------------

    /**
     * 结构转�?�, 详情�?��?: {@link StructureConvert}
     * 
     * @param maplist
     *            maplist结构的对象
     * @param model
     *            转�?�模�?�, 一个JSON格�?的reader
     */
    public static Object convert(Object maplist, Reader model) {
        StructureConvert convert = new StructureConvert(model);
        return convert.convert(maplist);
    }

    /**
     * 结构转�?�, 详情�?��?: {@link StructureConvert}
     * 
     * @param maplist
     *            maplist结构的对象
     * @param model
     *            转�?�模�?�, 也是一个规定格�?的maplist结构
     */
    public static Object convert(Object maplist, Object model) {
        StructureConvert convert = new StructureConvert(model);
        return convert.convert(maplist);
    }

    // ------------------------------------------------------------------
    /**
     * 将对象转�?��?Maplist结构
     * 
     * @param obj
     *            待转�?�的对象
     */
    public static Object toMaplist(Object obj) {
        ObjCompileImpl convert = new ObjCompileImpl();
        return convert.parse(obj);
    }

    /**
     * 添加新的结点
     * 
     * @param obj
     *            原始的MapList
     * @param path
     *            路径
     * @param val
     *            值
     */
    public static void put(Object obj, String path, Object val) {
        Object mapList = Mapl.toMaplist(val);
        MaplRebuild rebuild = new MaplRebuild(obj);
        rebuild.put(path, mapList);
    }

    /**
     * 删除一个结点
     * 
     * @param obj
     *            原始的Maplist
     * @param path
     *            路径
     */
    public static void del(Object obj, String path) {
        MaplRebuild rebuild = new MaplRebuild(obj);
        rebuild.remove(path);
    }

    /**
     * 更新
     * 
     * @param obj
     *            原始的Maplist
     * @param path
     *            路径
     * @param val
     *            新的值
     */
    public static void update(Object obj, String path, Object val) {
        put(obj, path, val);
    }
}
