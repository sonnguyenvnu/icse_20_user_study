package org.nutz.castor.castor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.nutz.castor.Castor;
import org.nutz.castor.FailToCastObjectException;
import org.nutz.lang.Lang;

@SuppressWarnings({"rawtypes"})
public class Number2Enum extends Castor<Number, Enum> {

    @Override
    public Enum cast(Number src, Class<?> toType, String... args)
            throws FailToCastObjectException {
        int v = src.intValue();
        Enum o = null;

        // 首先试图用采用该类型的 fromInt 的�?��?方法
        try {
            Method m = toType.getMethod("fromInt", int.class);
            if (Modifier.isStatic(m.getModifiers())
                && toType.isAssignableFrom(m.getReturnType())) {
                o = (Enum) m.invoke(null, v);
            }
        }
        catch (Exception e) {}
        // �?试图用采用该类型的 from 的�?��?方法
        if (null == o) {
            try {
                Method m = toType.getMethod("from", int.class);
                if (Modifier.isStatic(m.getModifiers())
                        && toType.isAssignableFrom(m.getReturnType())) {
                    o = (Enum) m.invoke(null, v);
                }
            } catch (Exception e) {
            }
        }
        // �?��?定，则试图根�?�顺�?�?�获�?�
        if (null == o)
            try {
                for (Field field : toType.getFields()) {
                    if (field.getType() == toType) {
                        Enum em = (Enum) field.get(null);
                        if (em.ordinal() == v)
                            return em;
                    }
                }
                throw Lang.makeThrow(FailToCastObjectException.class,
                                     "Can NO find enum value in [%s] by int value '%d'",
                                     toType.getName(),
                                     src.intValue());
            }
            catch (Exception e2) {
                throw Lang.wrapThrow(e2, FailToCastObjectException.class);
            }

        return o;
    }
}
