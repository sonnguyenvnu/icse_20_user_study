package org.nutz.dao.impl.entity.info;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.nutz.dao.DaoException;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Next;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.PK;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Readonly;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.lang.eject.EjectByGetter;
import org.nutz.lang.inject.InjectBySetter;
import org.nutz.lang.util.Callback3;

public class _Infos {

    private final static String ERR_MSG = "Method '%s'(%s) can not add '@Column', it MUST be a setter or getter!";

    @SuppressWarnings("rawtypes")
    private static <T extends FieldInfo> T create(Class<T> classOfT, Field field) {
        T info = Mirror.me(classOfT).born();
        info.name = field.getName();
        // XXX 兼容性改�?� 从1.b.51开始, 优先走getter/setter
        // �?版本是�?�从属性�?�值/设置值,�?走getter/setter
        info.fieldType = field.getGenericType();
        Mirror me = Mirror.me(field.getDeclaringClass());
        info.injecting = me.getInjecting(field.getName());
        info.ejecting = me.getEjecting(field.getName());
        return info;
    }

    private static <T extends FieldInfo> T create(Class<T> classOfT, final Method method) {
        final T info = Mirror.me(classOfT).born();
        Mirror.evalGetterSetter(method, ERR_MSG, new Callback3<String, Method, Method>() {
            public void invoke(String name, Method getter, Method setter) {
                // 木有 getter
                if (null == getter) {
                    throw Lang.makeThrow(    "Method '%s'(%s) has '@Column', but NO setter!",
                                            method.getName(),
                                            method.getDeclaringClass().getName());
                }
                // 木有 setter
                if (null == setter) {
                    throw Lang.makeThrow(    "Method '%s'(%s) has '@Column', but NO setter!",
                                            method.getName(),
                                            method.getDeclaringClass().getName());
                }
                // 正常，开始设值
                info.name = name;
                info.fieldType = getter.getGenericReturnType();
                info.ejecting = new EjectByGetter(getter);
                info.injecting = new InjectBySetter(setter);
            }
        });
        return info;
    }

    public static LinkInfo createLinkInfo(Method method) {
        LinkInfo info = create(LinkInfo.class, method);
        info.one = method.getAnnotation(One.class);
        info.many = method.getAnnotation(Many.class);
        info.manymany = method.getAnnotation(ManyMany.class);
        return info;
    }

    public static LinkInfo createLinkInfo(Field field) {
        LinkInfo info = create(LinkInfo.class, field);
        info.one = field.getAnnotation(One.class);
        info.many = field.getAnnotation(Many.class);
        info.manymany = field.getAnnotation(ManyMany.class);
        info.comment = field.getAnnotation(Comment.class);
        return info;
    }

    /**
     * 根�?� getter/setter 函数获�?�一个实体字段信�?�对象
     * 
     * @param pk
     *            �?�?�主键
     * @param method
     *            方法：�?�能是 getter 或者是 setter
     * @return 字段信�?�对象
     */
    public static MappingInfo createMapingInfo(PK pk, Method method) {
        MappingInfo info = create(MappingInfo.class, method);
        info.annPK = pk;
        info.annColumn = method.getAnnotation(Column.class);
        info.annDefine = method.getAnnotation(ColDefine.class);
        info.annDefault = method.getAnnotation(Default.class);
        info.annId = method.getAnnotation(Id.class);
        info.annName = method.getAnnotation(Name.class);
        info.annNext = method.getAnnotation(Next.class);
        info.annPrev = method.getAnnotation(Prev.class);
        info.annReadonly = method.getAnnotation(Readonly.class);
        return info;
    }

    /**
     * 根�?� Java 字段创建一个实体字段信�?�对象
     * 
     * @param pk
     *            �?�?�主键
     * @param field
     *            Java 字段
     * @return 字段信�?�对象
     */
    public static MappingInfo createMappingInfo(PK pk, Field field) {
        MappingInfo info = create(MappingInfo.class, field);
        info.annPK = pk;
        info.annColumn = field.getAnnotation(Column.class);
        info.annDefine = field.getAnnotation(ColDefine.class);
        info.annDefault = field.getAnnotation(Default.class);
        info.annId = field.getAnnotation(Id.class);
        info.annName = field.getAnnotation(Name.class);
        info.annNext = field.getAnnotation(Next.class);
        info.annPrev = field.getAnnotation(Prev.class);
        info.annReadonly = field.getAnnotation(Readonly.class);
        info.columnComment = field.getAnnotation(Comment.class);
        
        //检查@Id和@Name的属性类型
        if (info.annId != null) {
            if (!Mirror.me(field.getType()).isIntLike())
                throw Lang.makeThrow(DaoException.class, "Field(%s) annotation @Id , but not Number type!!", field);
        }
        
        if (info.annName != null)
            if (!Mirror.me(field.getType()).isStringLike())
                throw Lang.makeThrow(DaoException.class, "Field(%s) annotation @Name , but not String type!!", field);
        //检查@Version属性类型，必须是int�?long�?short
        if (info.annColumn != null && info.annColumn.version()){
        	Mirror<?> mirror =Mirror.me(field.getType());
        	if (!mirror.isInt() && !mirror.isShort() && !mirror.isLong())
        		throw Lang.makeThrow(DaoException.class, "Field(%s) define version=true , but not  int\\long\\short type!", field);
        }
        
        return info;
    }

}
