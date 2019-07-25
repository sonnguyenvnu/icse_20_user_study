package org.nutz.el.opt.logic;

import org.nutz.el.opt.TwoTernary;
import org.nutz.lang.Lang;

/**
 * A ||| B 如果A是null, 或者A是数组/集�?�/Map但长度是0,返回B
 * 
 * @author wendal(wendal1985@gmail.com)
 *
 */
public class OrOpt2 extends TwoTernary {

    public int fetchPriority() {
        return 12;
    }

    public Object calculate() {
        Object lval = calculateItem(left);
        if (Lang.eleSize(lval) > 0) {
            return lval;
        }
        return calculateItem(right);
    }

    public String fetchSelf() {
        return "|||";
    }

}
