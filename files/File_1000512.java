package org.nutz.el.opt.logic;

import org.nutz.castor.Castors;
import org.nutz.el.opt.TwoTernary;

/**
 * or(||)
 * 
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class OrOpt extends TwoTernary {

    public int fetchPriority() {
        return 12;
    }

    public Object calculate() {
        Object lval = calculateItem(left);
        if (null != lval) {
            if (!(lval instanceof Boolean)) {
                // throw new ElException("�?作数类型错误!");
                if (Castors.me().castTo(lval, Boolean.class)) {
                    return true;
                }
            } else if ((Boolean) lval) {
                return true;
            }
        }
        Object rval = calculateItem(right);
        if (null != rval) {
            if (!(rval instanceof Boolean)) {
                // throw new ElException("�?作数类型错误!");
                return Castors.me().castTo(rval, Boolean.class);
            }
            if ((Boolean) rval) {
                return true;
            }
        }
        return false;
    }

    public String fetchSelf() {
        return "||";
    }

}
