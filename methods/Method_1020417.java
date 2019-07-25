/** 
 * trace handles methods executed with the `@Traced` annotation. A new span will be created with an optionally customizable span name.
 * @param call the join point to execute
 * @return the result of the invocation
 * @throws Throwable if the underlying target throws an exception
 * @since 0.16.0
 */
@Around("@annotation(io.opencensus.contrib.spring.aop.Traced)") public Object trace(ProceedingJoinPoint call) throws Throwable {
  MethodSignature signature=(MethodSignature)call.getSignature();
  Method method=signature.getMethod();
  Traced annotation=method.getAnnotation(Traced.class);
  if (annotation == null) {
    return call.proceed();
  }
  String spanName=annotation.name();
  if (spanName.isEmpty()) {
    spanName=method.getName();
  }
  return Handler.proceed(call,tracer,spanName);
}
