package org.nutz.mapl.impl.convert;

import java.util.ArrayList;
import java.util.List;

import org.nutz.json.Json;
import org.nutz.lang.Streams;
import org.nutz.mapl.MaplConvert;
import org.nutz.mapl.impl.MaplEach;
import org.nutz.mapl.impl.MaplRebuild;

/**
 * Jsonè¿‡æ»¤, 
 * <p>
 * æ ¹æ?®æ¨¡æ?¿å°†åŽŸå§‹çš„JSONè¿›è¡Œè¿‡æ»¤, å?ªæ˜¾ç¤ºä¸€éƒ¨åˆ†. ä¸?å?šè½¬æ?¢.
 * <p>
 * åŒ…æ‹¬ä¸¤ç§?æ¨¡å¼?, å?³, åŒ…å?«, æŽ’é™¤æ¨¡å¼?, é»˜è®¤ä¸ºæŽ’é™¤æ¨¡å¼?.
 * <p>
 * 
 * @author juqkai(juqkai@gmail.com)
 */
public class FilterConvertImpl extends MaplEach implements MaplConvert{
    //å¤„ç?†åˆ—è¡¨
    private List<String> items = new ArrayList<String>();
    //ç±»åž‹, å?–å??è‡ªexclude(æŽ’é™¤), include(åŒ…å?«), falseæ—¶ä¸ºæŽ’é™¤, trueæ—¶ä¸ºåŒ…å?«
    private boolean clude = false;
    private MaplRebuild build = new MaplRebuild();
    
    @SuppressWarnings("unchecked")
    public FilterConvertImpl(String path) {
        items = (List<String>) Json.fromJson(Streams.fileInr(path));
    }
    
    public FilterConvertImpl(List<String> paths){
        this.items = paths;
    }
    
    /**
     * è½¬æ?¢
     * @param obj ç›®æ ‡å¯¹è±¡
     */
    public Object convert(Object obj){
        each(obj);
        return build.fetchNewobj();
    }
    
    protected void DLR(String path, Object item) {
        if(clude){
            if(items.contains(path)){
                build.put(path, item, arrayIndex);
            } 
        }
    }

    protected void LRD(String path, Object item) {
        if(clude){
           return;
        }
        int isFilter = 0;
        for(String p : items){
            if(
                    p.equals(path) 
                    || path.startsWith((p + ".")) 
                    || p.startsWith(path + ".") 
                    || path.startsWith((p + "[]")) 
                    || p.startsWith(path + "[]") 
            ){
                isFilter++;
            }
        }
        if(isFilter == 0){
            build.put(path, item, arrayIndex);
        }
    }


    public void useExcludeModel() {
        this.clude = false;
    }
    public void useIncludeModel() {
        this.clude = true;
    }
}
