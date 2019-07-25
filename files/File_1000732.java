package org.nutz.mapl.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * é€’å½’MapListç»“æž„, å°†è·¯å¾„ä¸Žç›¸åº”çš„å€¼ä¼ é€’ç»™å­?ç±»è¿›è¡Œå¤„ç?†.
 * @author juqkai(juqkai@gmail.com)
 */
public abstract class MaplEach {
    //è·¯å¾„
    protected LinkedList<String> paths = new LinkedList<String>();
    protected LinkedList<Integer> arrayIndex = new LinkedList<Integer>();
    /**
     * è½¬æ?¢å¯¹è±¡
     * @param obj
     */
    protected void each(Object obj) {
        if(obj instanceof Map){
            convertMap((Map<?, ?>) obj);
        } else if(obj instanceof List){
            convertList((List<?>) obj);
        }
    }
    /**
     * è½¬æ?¢map
     * @param obj
     */
    private void convertMap(Map<?, ?> obj) {
        for(Object key : obj.keySet()){
            paths.addLast(key.toString());
            DLR(fetchPath(), obj.get(key));
            each(obj.get(key));
            LRD(fetchPath(), obj.get(key));
            paths.removeLast();
        }
    }
    /**
     * æ??å?–è·¯å¾„
     * @return
     */
    private String fetchPath(){
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        for(String path : paths){
            if(!first){
                sb.append(".");
            }
            sb.append(path);
            first = false;
        }
        return sb.toString();
    }
    /**
     * è½¬æ?¢LIST
     * @param val
     */
    private void convertList(List<?> val){
        if(paths.size() <= 0){
            paths.add("[]");
        }else{
            paths.addLast(paths.removeLast() + "[]");
        }
        for(int i = 0; i < val.size(); i++){
            arrayIndex.addLast(i);
            each(val.get(i));
            arrayIndex.removeLast();
        }
    }
    
    /**
     * å‰?åº?
     * @param path
     * @param item
     */
    protected abstract void DLR(String path, Object item);
    /**
     * å?Žåº?
     * @param path
     * @param item
     */
    protected abstract void LRD(String path, Object item);
}
