package org.nutz.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;

import org.nutz.aop.DefaultClassDefiner;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.repo.org.objectweb.asm.ClassWriter;
import org.nutz.repo.org.objectweb.asm.Label;
import org.nutz.repo.org.objectweb.asm.Opcodes;
import org.nutz.repo.org.objectweb.asm.Type;
import org.nutz.repo.org.objectweb.asm.commons.GeneratorAdapter;

public class FastMethodFactory implements Opcodes {

    protected static ConcurrentHashMap<String, FastMethod> cache = new ConcurrentHashMap<String, FastMethod>();
    protected static final Log log = Logs.get();

    protected static org.nutz.repo.org.objectweb.asm.commons.Method _M;
    protected static org.nutz.repo.org.objectweb.asm.Type Exception_TYPE;
    static {
        _M = _Method("invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;");
        Exception_TYPE = Type.getType(Throwable.class);
    }
    
    protected static FastMethod make(final Method method) {
        Class<?> klass = method.getDeclaringClass();
        String descriptor = Type.getMethodDescriptor(method) + method.getDeclaringClass().getClassLoader();
        String key = "$FM$" + method.getName() + "$" + Lang.md5(descriptor);
        String className = klass.getName() + key;
        if (klass.getName().startsWith("java"))
            className = FastMethod.class.getPackage().getName() + ".fast." + className;
        FastMethod fm = cache.get(className);
        if (fm != null)
            return fm;
        // fix issue #1382 : �?�public类的方法,统统�?��?FallbackFastMethod
        if (!Modifier.isPublic(klass.getModifiers())) {
            fm = new FallbackFastMethod(method);
            cache.put(className, fm);
            return fm;
        }
        try {
            fm = (FastMethod) klass.getClassLoader().loadClass(className).newInstance();
            cache.put(className, fm);
            return fm;
        }
        catch (Throwable e) {}
        try {
            byte[] buf = _make(klass,
                               method.getModifiers(),
                               method.getParameterTypes(),
                               _Method(method),
                               method.getReturnType(),
                               className,
                               descriptor);
            Class<?> t = DefaultClassDefiner.defaultOne().define(className,
                                                                 buf,
                                                                 klass.getClassLoader());
            fm = (FastMethod) t.newInstance();
        }
        catch (Throwable e) {
            if (log.isTraceEnabled())
                log.trace("Fail to create FastMethod for " + method, e);
            fm = new FallbackFastMethod(method);
        }
        cache.put(className, fm);
        return fm;
    }

    protected static FastMethod make(Constructor<?> constructor) {
        Class<?> klass = constructor.getDeclaringClass();
        String descriptor = Type.getConstructorDescriptor(constructor) + constructor.getDeclaringClass().getClassLoader();;
        String key = Lang.md5(descriptor);
        String className = klass.getName() + "$FC$" + key;
        if (klass.getName().startsWith("java"))
            className = FastMethod.class.getPackage().getName() + ".fast." + className;
        FastMethod fm = (FastMethod) cache.get(className);
        if (fm != null)
            return fm;
        try {
            fm = (FastMethod) klass.getClassLoader().loadClass(className).newInstance();
            cache.put(key, fm);
            return fm;
        }
        catch (Throwable e) {}
        try {
            byte[] buf = _make(klass,
                               constructor.getModifiers(),
                               constructor.getParameterTypes(),
                               _Method(constructor),
                               null,
                               className,
                               descriptor);
            Class<?> t = DefaultClassDefiner.defaultOne().define(className,
                                                                 buf,
                                                                 klass.getClassLoader());
            fm = (FastMethod) t.newInstance();
        }
        catch (Throwable e) {
            if (log.isTraceEnabled())
                log.trace("Fail to create FastMethod for " + constructor, e);
            fm = new FallbackFastMethod(constructor);
        }
        cache.put(className, fm);
        return fm;
    }

    /**
     * 生�?两�?FastClass实例: 创建对象, 和执行普通方法的. 区分的点就是returnType是�?�为null. 模�?为创建对象,returnType总是null, 模�?为执行方法,returnType总�?是null, �?么Void�?么是�?个类.
     * @param klass 被代�?�的类
     * @param mod 方法的modify数�?�
     * @param params �?�数列表
     * @param method asm下的方法签�??
     * @param returnType 返回值. 如果执行的是构造方法,那么它是null,�?�则肯定�?是null.
     * @param className 目标类�??,FastClass实现类的类�??
     * @param descriptor 类签�??
     */
    public static byte[] _make(Class<?> klass,
                               int mod,
                               Class<?>[] params,
                               org.nutz.repo.org.objectweb.asm.commons.Method method,
                               Class<?> returnType,
                               String className,
                               String descriptor) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_5,
                 ACC_PUBLIC,
                 className.replace('.', '/'),
                 null,
                 "java/lang/Object",
                 new String[]{FastMethod.class.getName().replace('.', '/')});
        
        Type klassType = Type.getType(klass);

        // 首先, 定义默认构造方法
        addConstructor(cw, Type.getType(Object.class), org.nutz.repo.org.objectweb.asm.commons.Method.getMethod("void <init> ()"));

        // 然�?�定义执行方法, _M就是invoke方法的签�??
        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, _M, null, new Type[]{Exception_TYPE}, cw);
        if (returnType == null) { // 没有返回值,是代表这里模�?是 "构造方法"
            mg.newInstance(klassType); // 相当于 new User . 注�?, 没有括�?�哦,因为构造方法�?�数还没传
            mg.dup();
        }
        else if (!Modifier.isStatic(mod)) { // 有返回值, 那么, 模�?是"执行方法". 然�?�呢, �?��?��?方法的�?,需�?载入对象
            mg.loadThis();
            mg.loadArg(0); // 代表 Object invoke(Object obj, Object ... args) 中的 obj
            mg.checkCast(klassType); // 因为invoke方法的签�??中, obj的类型是Object, 需�?cast为目标类型.
            // 相当于执行了 ((User)obj)
        }
        // 准备�?�数列表. 根�?�被执行的方法或构造方法的签�??,�?�以推测出�?�数列表.
        // invoke方法得到的是一个 Object[], 需�?一一展开
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                mg.loadArg(1); // 代表 Object invoke(Object obj, Object ... args) 中的 args
                mg.push(i); // 数组下标
                mg.arrayLoad(Type.getType(Object.class)); // 读�?�数组. 上�?�三�?�相当于 args[i]
                Type paramType = Type.getType(params[i]); // 读�?�目标方法/构造方法的�?�数的类型
                if (params[i].isPrimitive()) {
                    mg.unbox(paramType); // 如果是基本数�?�类型,需�?开箱. Object --> Integer --> int . 在Java�?文件里�?�有自动�?箱/自动开箱,asm�?�没这�?东西. 
                } else {
                    mg.checkCast(paramType); // 其他类型? 直接cast一下就好了
                }
            }
            // 上�?�的代�?转�?�为java写法的�?,就是
            // ((Integer)args[0], (String)args[1], ....)
            // 注�?, 没有 对象在�?�?�, 因为还没执行
        }
        if (returnType == null) { // 模�?是
            mg.invokeConstructor(klassType, method);
            // 跟�?�?�的代�?�?�并在一起        new User((Integer)args[0], (String)args[1], ....);
        } else {
            if (Modifier.isStatic(mod)) {
                mg.invokeStatic(klassType, method);
                // 跟�?�?�的代�?�?�并在一起,�?�设方法�??称是create,�?��?方法
                // User.create((Integer)args[0], (String)args[1], ....);
            } else if (Modifier.isInterface(klass.getModifiers())) {
                mg.invokeInterface(klassType, method);
                // 跟�?�?�的代�?�?�并在一起,�?�设方法�??称是create, User是一个接�?�
                // ((User)obj).create((Integer)args[0], (String)args[1], ....);
            } else {
                mg.invokeVirtual(klassType, method);
                // 跟�?�?�的代�?�?�并在一起,�?�设方法�??称是create, User是一个普通类
                // ((User)obj).create((Integer)args[0], (String)args[1], ....);
            }
            // 处�?�方法返回值的特殊情况
            if (Void.class.equals(returnType)) {
                // 如果method没有返回值(void),那么,塞入一个null�?�invoke方法返回值
                mg.visitInsn(ACONST_NULL);
            } else if (returnType.isPrimitive()) {
                // method有返回值,而且是基本数�?�类型? 那么就�?�?箱了,因为invoke方法返回的是Object, 基本数�?�类型�?��?是Object.
                mg.box(Type.getType(returnType));
            } else {
                // 其余的情况就是没情况, method的返回值已�?在堆栈里�?�,等�?�返回就行
            }
        }
        // 伪造一下行�?�, 这样eclipse就�?会抱怨
        Label tmp = new Label();
        mg.visitLabel(tmp);
        mg.visitLineNumber(1, tmp);
        
        // 把堆栈中的返回值给弹出去.
        mg.returnValue();
        // 完整整个方法
        mg.endMethod();
        // �?注册一下�?文件�??称, 结�?�行�?�, 日志里�?�会显示 (User.java:1)
        cw.visitSource(klass.getSimpleName() + ".java", null);
        // 整个类结�?�
        cw.visitEnd();
        // 字节�?生�?完�?, 返回byte[]
        return cw.toByteArray();
    }

    public static org.nutz.repo.org.objectweb.asm.commons.Method _Method(String name, String desc) {
        return new org.nutz.repo.org.objectweb.asm.commons.Method(name, desc);
    }

    public static org.nutz.repo.org.objectweb.asm.commons.Method _Method(Method method) {
        return org.nutz.repo.org.objectweb.asm.commons.Method.getMethod(method);
    }

    public static org.nutz.repo.org.objectweb.asm.commons.Method _Method(String method) {
        return org.nutz.repo.org.objectweb.asm.commons.Method.getMethod(method);
    }

    private static org.nutz.repo.org.objectweb.asm.commons.Method _Method(Constructor<?> constructor) {
        return org.nutz.repo.org.objectweb.asm.commons.Method.getMethod(constructor);
    }

    public static void addConstructor(ClassWriter cw,
                                      Type parent,
                                      org.nutz.repo.org.objectweb.asm.commons.Method m) {
        GeneratorAdapter mg = new GeneratorAdapter(ACC_PUBLIC, m, null, null, cw);
        mg.loadThis();
        mg.loadArgs();
        mg.invokeConstructor(parent, m);
        mg.returnValue();
        mg.endMethod();
    }
    
    public static class FallbackFastMethod implements FastMethod {
        
        public Method method;
        
        public Constructor<?> constructor;
        
        public FallbackFastMethod(Method method) {
            this.method = method;
            if (!this.method.isAccessible())
                this.method.setAccessible(true);
        }

        public FallbackFastMethod(Constructor<?> constructor) {
            this.constructor = constructor;
            if (!this.constructor.isAccessible())
                this.constructor.setAccessible(true);
        }

        public Object invoke(Object obj, Object... args) throws Exception {
            if (method == null)
                return constructor.newInstance(args);
            return method.invoke(obj, args);
        }
        
    }
}
