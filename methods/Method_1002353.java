@org.aspectj.lang.annotation.Around("@within(org.bytesoft.compensable.Compensable)") public Object invoke(final ProceedingJoinPoint pjp) throws Throwable {
  CompensableManager compensableManager=this.beanFactory.getCompensableManager();
  CompensableTransaction compensable=compensableManager.getCompensableTransactionQuietly();
  if (compensable != null && compensable.getTransactionContext() != null && compensable.getTransactionContext().isCompensating()) {
    return pjp.proceed();
  }
  Object bean=pjp.getThis();
  String identifier=this.getBeanName(bean);
  MethodSignature signature=(MethodSignature)pjp.getSignature();
  Method method=signature.getMethod();
  Object[] args=pjp.getArgs();
  AspectJoinpoint point=new AspectJoinpoint(pjp);
  return this.execute(identifier,method,args,point);
}
