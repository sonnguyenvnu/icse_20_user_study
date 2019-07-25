package org.nutz.aop.interceptor;

import java.lang.reflect.Method;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;

/**
 * �??供一个基础实现,这个实现,既�?�以简化用户的实现,�?��?�以实现与以�?的Aop拦截器的兼容
 * @author wendal(wendal1985@gmail.com)
 *
 */
public class AbstractMethodInterceptor implements MethodInterceptor {

    /**
     * 拦截方法调用, 将拦截器的行为, 分�?: 之�?,之�?�,抛异常,抛错误 -- 4�?拦截点
     */
    public void filter(InterceptorChain chain) throws Throwable {
        try {
            if (beforeInvoke(chain.getCallingObj(), chain.getCallingMethod(), chain.getArgs()))
                chain.doChain();
            Object obj = afterInvoke(    chain.getCallingObj(),
                                        chain.getReturn(),
                                        chain.getCallingMethod(),
                                        chain.getArgs());
            chain.setReturnValue(obj);
        }
        catch (Exception e) {
            if (whenException(e, chain.getCallingObj(), chain.getCallingMethod(), chain.getArgs()))
                throw e;
        }
        catch (Throwable e) {
            if (whenError(e, chain.getCallingObj(), chain.getCallingMethod(), chain.getArgs()))
                throw e;
        }

    }

    /**
     * 在方法执行�?拦截
     * @param obj 被拦截的对象
     * @param method 被拦截的方法
     * @param args 被拦截的方法的�?�数
     * @return 如果继续往下走,就返回true,�?�则就退出AOP执行链
     */
    public boolean beforeInvoke(Object obj, Method method, Object... args) {
        return true;
    }
    
    /**
     * 在方法执行�?�拦截
     * @param obj 被拦截的对象
     * @param returnObj 被拦截的方法的返回值的对象
     * @param method 被拦截的方法
     * @param args 被拦截方法的�?�数
     * @return 将会替代原方法返回值的值
     */
    public Object afterInvoke(Object obj, Object returnObj, Method method, Object... args) {
        return returnObj;
    }
    
    /**
     * 抛出Exception的时候拦截
     * @param e 异常对象
     * @param obj 被拦截的对象
     * @param method 被拦截的方法
     * @param args 被拦截方法的返回值
     * @return 是�?�继续抛出异常
     */
    public boolean whenException(Exception e, Object obj, Method method, Object... args) {
        return true;
    }

    /**
     * 抛出Throwable的时候拦截
     * @param e 异常对象
     * @param obj 被拦截的对象
     * @param method 被拦截的方法
     * @param args 被拦截方法的返回值
     * @return 是�?�继续抛出异常
     */
    public boolean whenError(Throwable e, Object obj, Method method, Object... args) {
        return true;
    }

}
