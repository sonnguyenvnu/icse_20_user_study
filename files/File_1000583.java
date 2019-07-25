package org.nutz.lang.born;

import java.lang.reflect.Array;

import org.nutz.lang.Lang;

/**
 * �?装了生�?一个数组对象的方�?，它 born 的时候，需�?一个�?�数表示数组长度
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class ArrayBorning implements Borning<Object> {

    private Class<?> eleType;

    public ArrayBorning(Class<?> eleType) {
        this.eleType = eleType;
    }

    public Object born(Object... args) {
        // 第一个�?�数必须为整数
        if (args.length >= 1) {
            Object arg0 = args[0];
            if (arg0 instanceof Number) {
                return Array.newInstance(eleType, ((Number) arg0).intValue());
            }
        }
        throw Lang.makeThrow("array borning need length, arg0 should be number");
    }

}
