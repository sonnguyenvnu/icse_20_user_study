package org.nutz.mvc.adaptor.injector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象路径节点转�?�.<br/>
 * 将URL中的字符串�?�数�??转�?��?对结构, 然�?�通过 {@link org.nutz.mapl.Mapl}转�?��?实体对象<br/>
 * URL规则:
 * <ul>
 *  <li>对象与属性之间使用"."�?�为连接符
 *  <li>数组,Collection对象, 使用"[]"或":"�?�为索引引用符. <b style='color:red'>索引�?�是一个�?�考字段, �?会根�?�其值设置索引</b>
 *  <li>Map使用"()"或"."分割key值
 * </ul>
 * 例:<br> 
 * <code>
 * Object:  node.str = str<br>
 * list:    node.list[1].str = abc;<br>
 *          node.list:2.str = 2<br>
 * set:     node.set[2].str = bbb<br>
 *          node.set:jk.str = jk<br>
 * Map:     node.map(key).str = bb;<br>
 *          node.map.key.name = map<br>
 * 
 * </code>
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class ObjectNaviNode {
    private static final char separator = '.';
    private static final char LIST_SEPARATOR = ':';
    private static final int TYPE_NONE = 0;
    private static final int TYPE_LIST = 1;
    //节点�??
    private String name;
    //�?��?节点的值
    private String[] value;
    //是�?�是�?��?节点
    private boolean leaf = true;
    //�?节点
    private Map<String, ObjectNaviNode> child = new HashMap<String, ObjectNaviNode>();
    //类型
    private int type = TYPE_NONE;

    /**
     * �?始化当�?结点
     * 
     */
    public void put(String path, String[] value) {
        StringBuilder sb = new StringBuilder();
        char[] chars = path.toCharArray();
        OUT: for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
            case '[':
            case '(':
                i++;
                StringBuilder sb2 = new StringBuilder();
                boolean isNumber = true;
                for (; i < chars.length; i++) {
                    char c2 = chars[i];
                    switch (c2) {
                    case ']':
                    case ')':
                        if ((c == '[' && c2 == ']') || (c == '(' && c2 == ')')) {
                            if (isNumber && !(c == '(')) {
                                sb.append(':').append(sb2);
                            } else {
                                sb.append('.').append(sb2);
                            }
                            continue OUT;
                        }
                    }
                    isNumber = isNumber && Character.isDigit(c2);
                    sb2.append(c2);
                }
                break;
            default:
                sb.append(c);
                break;
            }
        }
        path = sb.toString();
        putPath(path, value);
    }
    
    private void putPath(String path, String[] value){
        init(path);
        String subPath = fetchSubPath(path); 
        if ("".equals(subPath) || path.equals(subPath)) {
            this.value = value;
            return;
        }
        leaf = false;
        addChild(subPath, value);
    }
    /**
     * 添加�?结点
     * 
     */
    private void addChild(String path, String[] value) {
        String subname = fetchName(path);
        ObjectNaviNode onn = child.get(subname);
        if (onn == null) {
            onn = new ObjectNaviNode();
        }
        onn.putPath(path, value);
        child.put(subname, onn);
    }
    
    /**
     * �?始化name, type信�?�
     * @param path
     */
    private void init(String path){
        String key = fetchNode(path);
        if(isList(key)){
            type = TYPE_LIST;
            name = key.substring(0, key.indexOf(LIST_SEPARATOR));
            return;
        }
        name = key;
    }
    
    /**
     * �??�?��?路径
     * @param path
     */
    private String fetchSubPath(String path){
        if(isList(fetchNode(path))){
            return path.substring(path.indexOf(LIST_SEPARATOR) + 1);
        }
        return path.substring(path.indexOf(separator) + 1);
    }
    

    
    /**
     * �?�得节点�??
     * 
     */
    private static String fetchNode(String path) {
        if (path.indexOf(separator) <= 0) {
            return path;
        }
        return path.substring(0, path.indexOf(separator));
    }
    
    
    /**
     * �?�得节点的name信�?�
     */
    private String fetchName(String path){
        String key = fetchNode(path);
        if(isList(key)){
            return key.substring(0, key.indexOf(LIST_SEPARATOR));
        }
        return key;
    }
    
    /**
     * �??�?�出list,map结构
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object get(){
        if(isLeaf()){
            return value == null ? null : value.length == 1 ? value[0] : value;
        }
        if(type == TYPE_LIST){
            // fix issue #1109, 列表的key需�?�?排
            List list = new ArrayList();
            List<Integer> keys = new ArrayList<Integer>();
            List<String> keys2 = new ArrayList<String>();
            
            for (String key : child.keySet()) {
                try {
                    keys.add(Integer.parseInt(key));
                }
                catch (NumberFormatException e) {
                    keys2.add(key);
                }
            }
            Collections.sort(keys);
            for(Integer index : keys){
                list.add(child.get(index.toString()).get());
            }
            for(String key2 : keys2){
                list.add(child.get(key2).get());
            }
            return list;
        }
        Map map = new HashMap();
        for(String o : child.keySet()){
            map.put(o, child.get(o).get());
        }
        return map;
    }
    
    /**
     * 是�?�是list节点
     * @param key
     */
    private static boolean isList(String key){
        return key.indexOf(LIST_SEPARATOR) > 0;
    }

    public String getName() {
        return name;
    }

    public String[] getValue() {
        return value;
    }

    public boolean isLeaf() {
        return leaf;
    }
}
