package org.nutz.ioc;

import java.util.Map;
import java.util.Map.Entry;

import org.nutz.ioc.meta.IocEventSet;
import org.nutz.ioc.meta.IocField;
import org.nutz.ioc.meta.IocObject;
import org.nutz.ioc.meta.IocValue;
import org.nutz.ioc.val.StaticValue;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.meta.Pair;
import org.nutz.lang.util.Regex;

/**
 * @author zozoh(zozohtnt@gmail.com)
 * @author wendal(wendal1985@gmail.com)
 * 
 */
public abstract class Iocs {

    private static final String OBJFIELDS = "^(type|scope|singleton|fields|args|events|factory)$";

    public static boolean isIocObject(Map<String, ?> map) {
        for (Entry<String, ?> en : map.entrySet())
            if (!Regex.match(OBJFIELDS, en.getKey()))
                return false;
        return true;
    }

    public static Pair<Class<?>> parseName(String name) {
        String _name = null;
        Class<?> type = null;
        int pos = name.indexOf(':');
        if (pos < 0) {
            _name = Strings.trim(name);
        } else {
            _name = Strings.trim(name.substring(0, pos));
            try {
                String typeName = Strings.trim(name.substring(pos + 1));
                type = Lang.loadClass(typeName);
            }
            catch (ClassNotFoundException e) {
                throw Lang.wrapThrow(e);
            }
        }
        return new Pair<Class<?>>(_name, type);
    }

    /**
     * 查看一下 me 中有没有缺少的属性，没有的�?，从 it 补充
     */
    public static IocObject mergeWith(IocObject me, IocObject it) {
        // merge type
        if (me.getType() == null)
            me.setType(it.getType());

        // don't need merge singleton

        // merge events
        if (me.getEvents() == null) {
            me.setEvents(it.getEvents());
        } else if (it.getEvents() != null) {
            IocEventSet eventSet = it.getEvents();
            IocEventSet myEventSet = me.getEvents();
            if (Strings.isBlank(myEventSet.getCreate()))
                myEventSet.setCreate(eventSet.getCreate());
            if (Strings.isBlank(myEventSet.getDepose()))
                myEventSet.setDepose(eventSet.getDepose());
            if (Strings.isBlank(myEventSet.getFetch()))
                myEventSet.setFetch(eventSet.getFetch());
        }

        // merge scope
        if (Strings.isBlank(me.getScope())) {
            me.setScope(it.getScope());
        }

        // merge arguments
        if (!me.hasArgs())
            me.copyArgys(it.getArgs());

        // merge fields
        for (IocField fld : it.getFields().values())
            if (!me.hasField(fld.getName()))
                me.addField(fld);

        return me;
    }
    
    public static IocValue convert(String value, boolean dftRefer) {
        IocValue iocValue = new IocValue();
        if (dftRefer && value.startsWith(":")) {
            iocValue.setType(IocValue.TYPE_NORMAL);
            iocValue.setValue(value.substring(1));
//        } else if (value.startsWith("$")) {
//            iocValue.setType(IocValue.TYPE_REFER);
//            iocValue.setValue(value.substring(1));
        }
        else if (value.contains(":")) {
            String type = value.substring(0, value.indexOf(':'));
            if (IocValue.types.contains(type)) {
                iocValue.setType(type);
                if (value.endsWith(":")) {
                    iocValue.setValue("");
                }
                else
                    iocValue.setValue(value.substring(value.indexOf(':') + 1));
            } else {
                iocValue.setType(IocValue.TYPE_NORMAL);
                iocValue.setValue(value);
            }
        } else {
            // XXX 兼容性改�?�, 1.b.52 开始默认就是refer, 如果真的�?输入常�?
            //log.info("auto set as         refer:" + value);
            iocValue.setType(IocValue.TYPE_REFER);
            iocValue.setValue(value);
        }
        return iocValue;
    }
    
    public static Object self(Object obj) {
        return obj;
    }
    
    public static IocObject wrap(Object obj) {
        IocObject iobj = new IocObject();
        if (obj != null)
            iobj.setType(obj.getClass());
        iobj.setFactory(Iocs.class.getName() + "#self");
        IocValue ival = new IocValue(null, new StaticValue(obj));
        iobj.addArg(ival);
        return iobj;
    }
}
