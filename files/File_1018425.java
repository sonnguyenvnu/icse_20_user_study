package com.xiaolyuh.aspect;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.xiaolyuh.annotation.Retryable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author yuhao.wang
 */
@Aspect
@Component
public class RetryAspect {
    private static final Logger logger = LoggerFactory.getLogger(RetryAspect.class);

    @Pointcut("@annotation(com.xiaolyuh.annotation.Retryable)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object retry(ProceedingJoinPoint joinPoint) throws Exception {
        // 获�?�注解
        Retryable retryable = AnnotationUtils.findAnnotation(getSpecificmethod(joinPoint), Retryable.class);
        // 声明Callable
        Callable<Object> task = () -> getTask(joinPoint);
        // 声明guava retry对象
        Retryer<Object> retryer = null;
        try {
            retryer = RetryerBuilder.newBuilder()
                    // 指定触�?��?试的�?�件
                    .retryIfResult(Predicates.isNull())
                    // 指定触�?��?试的异常
                    .retryIfExceptionOfType(retryable.exception())// 抛出Exception异常时�?试
                    // �?试次数
                    .withStopStrategy(StopStrategies.stopAfterAttempt(retryable.attemptNumber()))
                    // �?调策略
                    .withWaitStrategy(WaitStrategies.fixedWait(retryable.sleepTime(), retryable.timeUnit()))// 等待300毫秒
                    // 指定监�?�器RetryListener
                    .withRetryListener(retryable.retryListener().newInstance())
                    .build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        try {
            // 执行方法
            return retryer.call(task);
        } catch (ExecutionException e) {
            logger.error(e.getMessage(), e);
        } catch (RetryException e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    private Object getTask(ProceedingJoinPoint joinPoint) {
        // 执行方法，并获�?�返回值
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
            throw new RuntimeException("执行任务异常");
        }
    }

    private Method getSpecificmethod(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // The method may be on an interface, but we need attributes from the
        // target class. If the target class is null, the method will be
        // unchanged.
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(pjp.getTarget());
        if (targetClass == null && pjp.getTarget() != null) {
            targetClass = pjp.getTarget().getClass();
        }
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the
        // original method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        return specificMethod;
    }
}
