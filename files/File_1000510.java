package org.nutz.el.opt.logic;

import java.util.Queue;

import org.nutz.castor.Castors;
import org.nutz.el.opt.AbstractOpt;

/**
 * Not(!)
 * 
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class NotOpt extends AbstractOpt {
    private Object right;

    public int fetchPriority() {
        return 7;
    }

    public void wrap(Queue<Object> rpn) {
        right = rpn.poll();
    }

    public Object calculate() {
        Object rval = calculateItem(this.right);
        if (null == rval)
            return true;
        if (rval instanceof Boolean) {
            return !(Boolean) rval;
        }
        // throw new ElException("'!'�?作符�?作失败!");
        return !Castors.me().castTo(rval, Boolean.class);
    }

    public String fetchSelf() {
        return "!";
    }
}
