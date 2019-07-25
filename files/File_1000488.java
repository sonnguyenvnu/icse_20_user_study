package org.nutz.el.opt.arithmetic;

import java.util.Queue;

import org.nutz.el.ElException;
import org.nutz.el.opt.AbstractOpt;

/**
 * å?³æ‹¬å?·')'
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class RBracketOpt extends AbstractOpt{

    public int fetchPriority() {
        return 100;
    }
    public String fetchSelf() {
        return ")";
    }
    public void wrap(Queue<Object> obj) {
        throw new ElException("')ç¬¦å?·ä¸?èƒ½è¿›è¡Œwrapæ“?ä½œ!'");
    }
    public Object calculate() {
        throw new ElException("')'ç¬¦å?·ä¸?èƒ½è¿›è¡Œè®¡ç®—æ“?ä½œ!");
    }

}
