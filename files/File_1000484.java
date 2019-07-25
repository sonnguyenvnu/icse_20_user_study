package org.nutz.el.opt.arithmetic;

import java.util.Queue;

import org.nutz.el.ElException;
import org.nutz.el.opt.AbstractOpt;

/**
 * "("
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class LBracketOpt extends AbstractOpt{
    public String fetchSelf() {
        return "(";
    }
    public int fetchPriority() {
        return 100;
    }
    
    public void wrap(Queue<Object> obj) {
        throw new ElException("'('ç¬¦å?·ä¸?èƒ½è¿›è¡Œwrapæ“?ä½œ!");
    }
    public Object calculate() {
        throw new ElException("'('ç¬¦å?·ä¸?èƒ½è¿›è¡Œè®¡ç®—æ“?ä½œ!");
    }
}
