package org.nutz.el.opt.object;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.nutz.el.opt.TwoTernary;

/**
 * æ•°ç»„è¯»å?–
 * å°†'['å?šä¸ºè¯»å?–æ“?ä½œç¬¦ä½¿ç”¨,å®ƒè¯»å?–ä¸¤ä¸ªå?‚æ•°,ä¸€ä¸ªæ˜¯æ•°ç»„æœ¬èº«,ä¸€ä¸ªæ˜¯ä¸‹æ ‡
 * å¤šç»´æ•°ç»„,åˆ™æ˜¯å…ˆè¯»å‡ºä¸€ç»´,ç„¶å?Žå†?è¯»å?–ä¸‹ä¸€ç»´åº¦çš„æ•°æ?®
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class ArrayOpt extends TwoTernary {
    public int fetchPriority() {
        return 1;
    }
    @SuppressWarnings("rawtypes")
    public Object calculate() {
        Object lval = calculateItem(left);
        Object rval = calculateItem(right);
        
        //@ JKTODO è¿™é‡Œè¦?ä¸?è¦?ä¸Ž, AccessOpt é‡Œé?¢ç›¸å?Œçš„ä»£ç ?å?ˆå¹¶å‘¢?
        if(lval instanceof Map){
            Map<?,?> om = (Map<?, ?>) lval;
            if(om.containsKey(right.toString())){
                return om.get(right.toString());
            }
        } else if (lval instanceof List) {
            return ((List)lval).get((Integer)rval);
        }
        
        return Array.get(lval, (Integer)rval);
    }
    public String fetchSelf() {
        return "[";
    }
}
