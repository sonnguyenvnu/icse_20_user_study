/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.common.utils;

import com.alipay.sofa.rpc.core.exception.SofaRpcRuntimeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.alipay.sofa.rpc.common.utils.ClassLoaderUtils.getCurrentClassLoader;

/**
 * Class工具类
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 */
public final class ClassUtils {

    /**
     * 根�?�类�??加载Class
     *
     * @param className 类�??
     * @return Class
     */
    public static Class forName(String className) {
        return forName(className, true);
    }

    /**
     * 根�?�类�??加载Class
     *
     * @param className  类�??
     * @param initialize 是�?��?始化
     * @return Class
     */
    public static Class forName(String className, boolean initialize) {
        try {
            return Class.forName(className, initialize, getCurrentClassLoader());
        } catch (Exception e) {
            throw new SofaRpcRuntimeException(e);
        }
    }

    /**
     * 根�?�类�??加载Class
     *
     * @param className 类�??
     * @param cl        Classloader
     * @return Class
     */
    public static Class forName(String className, ClassLoader cl) {
        try {
            return Class.forName(className, true, cl);
        } catch (Exception e) {
            throw new SofaRpcRuntimeException(e);
        }
    }

    /**
     * 迭代查询全部方法，包括本类和父类
     *
     * @param clazz 对象类
     * @return 所有字段列表
     */
    public static List<Method> getAllMethods(Class clazz) {
        List<Method> all = new ArrayList<Method>();
        for (Class<?> c = clazz; c != Object.class && c != null; c = c.getSuperclass()) {
            Method[] methods = c.getDeclaredMethods(); // 所有方法，�?包�?�父类
            for (Method method : methods) {
                int mod = method.getModifiers();
                // native的�?�?
                if (Modifier.isNative(mod)) {
                    continue;
                }
                method.setAccessible(true); // �?管private还是protect都�?�以
                all.add(method);
            }
        }
        return all;
    }

    /**
     * 迭代查询全部字段，包括本类和父类
     *
     * @param clazz 对象类
     * @return 所有字段列表
     */
    public static List<Field> getAllFields(Class clazz) {
        List<Field> all = new ArrayList<Field>();
        for (Class<?> c = clazz; c != Object.class && c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields(); // 所有方法，�?包�?�父类
            for (Field field : fields) {
                int mod = field.getModifiers();
                // 过滤static 和 transient，支�?final
                if (Modifier.isStatic(mod) || Modifier.isTransient(mod)) {
                    continue;
                }
                field.setAccessible(true); // �?管private还是protect都�?�以
                all.add(field);
            }
        }
        return all;
    }

    /**
     * 实例化一个对象(�?�检测默认构造函数，其它�?管）
     *
     * @param clazz 对象类
     * @param <T>   对象具体类
     * @return 对象实例
     * @throws SofaRpcRuntimeException 没有找到方法，或者无法处�?�，或者�?始化方法异常等
     */
    public static <T> T newInstance(Class<T> clazz) throws SofaRpcRuntimeException {
        if (clazz.isPrimitive()) {
            return (T) getDefaultPrimitiveValue(clazz);
        }

        T t = getDefaultWrapperValue(clazz);
        if (t != null) {
            return t;
        }

        try {
            // 普通类，如果是�?员类（需�?多传一个父类�?�数）
            if (!(clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers()))) {
                try {
                    // 先找一个空的构造函数
                    Constructor<T> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return constructor.newInstance();
                } catch (Exception ignore) { // NOPMD
                }
            }
            // �?行的�?，找一个最少�?�数的构造函数
            Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
            if (constructors == null || constructors.length == 0) {
                throw new SofaRpcRuntimeException("The " + clazz.getCanonicalName()
                    + " has no default constructor!");
            }
            Constructor<T> constructor = constructors[0];
            if (constructor.getParameterTypes().length > 0) {
                for (Constructor<T> c : constructors) {
                    if (c.getParameterTypes().length < constructor.getParameterTypes().length) {
                        constructor = c;
                        if (constructor.getParameterTypes().length == 0) {
                            break;
                        }
                    }
                }
            }
            constructor.setAccessible(true);
            // 虚拟构造函数的�?�数值，基本类型使用默认值，其它类型使用null
            Class<?>[] argTypes = constructor.getParameterTypes();
            Object[] args = new Object[argTypes.length];
            for (int i = 0; i < args.length; i++) {
                args[i] = getDefaultPrimitiveValue(argTypes[i]);
            }
            return constructor.newInstance(args);
        } catch (SofaRpcRuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new SofaRpcRuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 实例化一个对象(根�?��?�数自动检测构造方法）
     *
     * @param clazz    对象类
     * @param argTypes 构造函数需�?的�?�数
     * @param args     构造函数需�?的�?�数
     * @param <T>      对象具体类
     * @return 对象实例
     * @throws SofaRpcRuntimeException 没有找到方法，或者无法处�?�，或者�?始化方法异常等
     */
    public static <T> T newInstanceWithArgs(Class<T> clazz, Class<?>[] argTypes, Object[] args)
        throws SofaRpcRuntimeException {
        if (CommonUtils.isEmpty(argTypes)) {
            return newInstance(clazz);
        }
        try {
            if (!(clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers()))) {
                Constructor<T> constructor = clazz.getDeclaredConstructor(argTypes);
                constructor.setAccessible(true);
                return constructor.newInstance(args);
            } else {
                Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
                if (constructors == null || constructors.length == 0) {
                    throw new SofaRpcRuntimeException("The " + clazz.getCanonicalName()
                        + " has no constructor with argTypes :" + Arrays.toString(argTypes));
                }
                Constructor<T> constructor = null;
                for (Constructor<T> c : constructors) {
                    Class[] ps = c.getParameterTypes();
                    if (ps.length == argTypes.length + 1) { // 长度多一
                        boolean allMath = true;
                        for (int i = 1; i < ps.length; i++) { // 而且第二个开始的�?�数类型匹�?
                            if (ps[i] != argTypes[i - 1]) {
                                allMath = false;
                                break;
                            }
                        }
                        if (allMath) {
                            constructor = c;
                            break;
                        }
                    }
                }
                if (constructor == null) {
                    throw new SofaRpcRuntimeException("The " + clazz.getCanonicalName()
                        + " has no constructor with argTypes :" + Arrays.toString(argTypes));
                } else {
                    constructor.setAccessible(true);
                    Object[] newArgs = new Object[args.length + 1];
                    System.arraycopy(args, 0, newArgs, 1, args.length);
                    return constructor.newInstance(newArgs);
                }
            }
        } catch (SofaRpcRuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new SofaRpcRuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 得到基本类型的默认值
     * 
     * @param clazz Class类
     * @return 默认值
     */
    public static Object getDefaultPrimitiveValue(Class clazz) {
        if (clazz == int.class) {
            return 0;
        } else if (clazz == boolean.class) {
            return false;
        } else if (clazz == long.class) {
            return 0L;
        } else if (clazz == byte.class) {
            return (byte) 0;
        } else if (clazz == double.class) {
            return 0d;
        } else if (clazz == short.class) {
            return (short) 0;
        } else if (clazz == float.class) {
            return 0f;
        } else if (clazz == char.class) {
            return (char) 0;
        } else {
            return null;
        }
    }

    /**
     * 得到包装类的默认值
     *
     * @param clazz Class类
     * @return 默认值
     */
    public static <T> T getDefaultWrapperValue(Class<T> clazz) {
        if (clazz == Short.class) {
            return (T) Short.valueOf((short) 0);
        } else if (clazz == Integer.class) {
            return (T) Integer.valueOf(0);
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(0L);
        } else if (clazz == Double.class) {
            return (T) Double.valueOf(0d);
        } else if (clazz == Float.class) {
            return (T) Float.valueOf(0f);
        } else if (clazz == Byte.class) {
            return (T) Byte.valueOf((byte) 0);
        } else if (clazz == Character.class) {
            return (T) Character.valueOf((char) 0);
        } else if (clazz == Boolean.class) {
            return (T) Boolean.FALSE;
        }
        return null;
    }

    /**
     * 得到方法关键字
     *
     * @param interfaceName 接�?��??
     * @param methodName    方法�??
     * @return 关键字
     */
    public static String getMethodKey(String interfaceName, String methodName) {
        return interfaceName + "#" + methodName;
    }

    /**
     * The isAssignableFrom method which can cross multiple classloader.
     *
     * @param interfaceClass 接�?�类
     * @param implementClass 实现类
     * @return 是�?�指定类型的实现类
     * @see Class#isAssignableFrom(Class) 
     */
    public static boolean isAssignableFrom(Class<?> interfaceClass, Class<?> implementClass) {
        if (interfaceClass.isAssignableFrom(implementClass)) {
            return true;
        }
        // 跨ClassLoader的情况
        String interfaceName = interfaceClass.getCanonicalName();
        return implementClass.getCanonicalName().equals(interfaceName)
            || isImplementOrSubclass(interfaceName, implementClass);
    }

    private static boolean isImplementOrSubclass(String interfaceName, Class<?> implementClass) {
        // First, get all direct interface
        Class<?>[] interfaces = implementClass.getInterfaces();
        if (interfaces.length > 0) {
            for (Class<?> oneInterface : interfaces) {
                if (interfaceName.equals(oneInterface.getCanonicalName())) {
                    return true;
                }
                if (isImplementOrSubclass(interfaceName, oneInterface)) {
                    return true;
                }
            }
        }
        while (!Object.class.equals(implementClass)) {
            // Add the super class
            Class<?> superClass = implementClass.getSuperclass();
            // Interfaces does not have java.lang.Object as superclass, they have null, so break the cycle and return
            if (superClass == null) {
                break;
            }
            // Now inspect the superclass
            implementClass = superClass;
            if (isImplementOrSubclass(interfaceName, implementClass)) {
                return true;
            }
        }
        return false;
    }
}
