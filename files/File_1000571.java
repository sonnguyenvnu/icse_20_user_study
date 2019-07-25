package org.nutz.json.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.nutz.json.JsonField;
import org.nutz.json.JsonIgnore;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.lang.eject.EjectByGetter;
import org.nutz.lang.eject.Ejecting;
import org.nutz.lang.inject.InjectBySetter;
import org.nutz.lang.inject.Injecting;
import org.nutz.lang.reflect.ReflectTool;

public class JsonEntityField {
	
    private String name;

    private boolean ignore;

    private Type genericType;

    private Injecting injecting;

    private Ejecting ejecting;

    private boolean forceString;
    
    private double ignoreNullDouble = -0.94518;
    
    private int ignoreNullInt = -94518;
    
    private boolean isInt;
    
    private boolean isDouble;
    
    private boolean hasJsonIgnore;
    
    private Format dataFormat;
    
    private Mirror<?> mirror;
    
    private Class<?> declaringClass;

    public boolean isForceString() {
        return forceString;
    }

    public void setForceString(boolean forceString) {
        this.forceString = forceString;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    /**
     * 根�?��??称获�?�字段实体, 默认以set优先
     */
    public static JsonEntityField eval(Mirror<?> mirror, String name, Method getter, Method setter) {
        JsonEntityField jef = new JsonEntityField();
        jef.declaringClass = mirror.getType();
        jef.setGenericType(getter.getGenericReturnType());
        jef.name = name;
        jef.ejecting = new EjectByGetter(getter);
        jef.injecting = new InjectBySetter(setter);
        jef.mirror = Mirror.me(getter.getReturnType());
        return jef;
    }

    public static JsonEntityField eval(Mirror<?> mirror, String name, Type type, Ejecting ejecting, Injecting injecting) {
        JsonEntityField jef = new JsonEntityField();
        jef.genericType = mirror.getType();
        jef.setGenericType(type);
        jef.name = name;
        jef.ejecting = ejecting;
        jef.injecting = injecting;
        jef.mirror = Mirror.me(type);
        return jef;
    }

    @SuppressWarnings({"deprecation", "rawtypes"})
    public static JsonEntityField eval(Mirror<?> mirror, Field fld) {
        if (fld == null) {
            return null;
        }

        // 以特殊字符开头的字段，看起�?�是�?�?字段
        // XXX 有用户就是_开头的字段也�?啊! by wendal
        // if (fld.getName().startsWith("_") || fld.getName().startsWith("$"))
        if (fld.getName().startsWith("$")
            && fld.getAnnotation(JsonField.class) == null)
            return null;

        JsonField jf = fld.getAnnotation(JsonField.class);

        JsonEntityField jef = new JsonEntityField();
        jef.declaringClass = mirror.getType();
        jef.setGenericType(Lang.getFieldType(mirror, fld));
        jef.name = Strings.sBlank(null == jf ? null : jf.value(), fld.getName());
        jef.ejecting = mirror.getEjecting(fld.getName());
        jef.injecting = mirror.getInjecting(fld.getName());
        jef.mirror = Mirror.me(fld.getType());

        // 瞬时�?��?和明确声明忽略的，�?� ignore
        if (Modifier.isTransient(fld.getModifiers())
            || (null != jf && jf.ignore())) {
            jef.setIgnore(true);
        }


        // 判断字段是�?�被强制输出为字符串
        if (null != jf) {
            jef.setForceString(jf.forceString());
            String dataFormat = jf.dataFormat();
            if(Strings.isBlank(dataFormat)){
                dataFormat = jf.dateFormat();
            }
            if(!Strings.isBlank(dataFormat)){
                Mirror jfmirror = Mirror.me(jef.genericType);
                if(jfmirror.isNumber()){
                    jef.dataFormat = new DecimalFormat(dataFormat);
                }else if(jfmirror.isDateTimeLike()){
                    DateFormat df = null;
                    if (Strings.isBlank(jf.locale())) {
                        df = new SimpleDateFormat(dataFormat);
                    }
                    else {
                        df = new SimpleDateFormat(dataFormat, Locale.forLanguageTag(jf.locale()));
                    }
                    if (!Strings.isBlank(jf.timeZone())) {
                        df.setTimeZone(TimeZone.getTimeZone(jf.timeZone()));
                    }
                    jef.dataFormat = df;
                }
            }
        }
        
        JsonIgnore jsonIgnore = fld.getAnnotation(JsonIgnore.class);
        if (jsonIgnore != null) {
            Mirror<?> fldMirror = Mirror.me(fld.getType());
            jef.isInt = fldMirror.isInt();
            jef.isDouble = fldMirror.isDouble() || fldMirror.isFloat();
        	jef.hasJsonIgnore = true;
            if (jef.isDouble)
            	jef.ignoreNullDouble = jsonIgnore.null_double();
            if (jef.isInt)
            	jef.ignoreNullInt = jsonIgnore.null_int();
        }
        
        return jef;
    }

    private JsonEntityField() {}

    public String getName() {
        return name;
    }

    public Type getGenericType() {
        return genericType;
    }

    public void setValue(Object obj, Object value) {
        if (injecting != null)
            injecting.inject(obj, value);
    }

    public Object getValue(Object obj) {
        if (ejecting == null)
            return null;
        Object val = ejecting.eject(obj);
        if (val == null)
        	return null;
        if (hasJsonIgnore) {
            if (isInt && ((Number)val).intValue() == ignoreNullInt)
            	return null;
            if (isDouble && ((Number)val).doubleValue() == ignoreNullDouble)
            	return null;
        }
        return val;
    }
    
    public Format getDataFormat() {
        return dataFormat == null ? null : (Format)dataFormat.clone();
    }
    
    public boolean hasDataFormat() {
        return dataFormat != null;
    }
    
    public Mirror<?> getMirror() {
        return mirror;
    }
    
    public void setGenericType(Type genericType) {
        this.genericType = ReflectTool.getInheritGenericType(declaringClass, genericType);;
    }
    
    public void setInjecting(Injecting injecting) {
        this.injecting = injecting;
    }
    
    public void setEjecting(Ejecting ejecting) {
        this.ejecting = ejecting;
    }
}
