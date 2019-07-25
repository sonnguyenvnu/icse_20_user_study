package org.nutz.el.opt.object;

import java.util.Queue;

import org.nutz.el.opt.AbstractOpt;

/**
 * ']',æ•°ç»„å°?è£….
 * æœ¬èº«æ²¡å?šä»€ä¹ˆæ“?ä½œ,å?ªæ˜¯å¯¹'[' ArrayOpt å?šäº†ä¸€ä¸ªå°?è£…è€Œå·²
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class FetchArrayOpt extends AbstractOpt {
    private Object left;
    public void wrap(Queue<Object> operand) {
        left = operand.poll();
    }
    public int fetchPriority() {
        return 1;
    }
    public Object calculate() {
        if(left instanceof ArrayOpt){
            return ((ArrayOpt) left).calculate();
        }
        return null;
    }
    public String fetchSelf() {
        return "]";
    }
}
