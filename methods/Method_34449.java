@Around("hystrixCommandAnnotationPointcut() || hystrixCollapserAnnotationPointcut()") public Object methodsAnnotatedWithHystrixCommand(final ProceedingJoinPoint joinPoint) throws Throwable {
  Method method=getMethodFromTarget(joinPoint);
  Validate.notNull(method,"failed to get method from joinPoint: %s",joinPoint);
  if (method.isAnnotationPresent(HystrixCommand.class) && method.isAnnotationPresent(HystrixCollapser.class)) {
    throw new IllegalStateException("method cannot be annotated with HystrixCommand and HystrixCollapser " + "annotations at the same time");
  }
  MetaHolderFactory metaHolderFactory=META_HOLDER_FACTORY_MAP.get(HystrixPointcutType.of(method));
  MetaHolder metaHolder=metaHolderFactory.create(joinPoint);
  HystrixInvokable invokable=HystrixCommandFactory.getInstance().create(metaHolder);
  ExecutionType executionType=metaHolder.isCollapserAnnotationPresent() ? metaHolder.getCollapserExecutionType() : metaHolder.getExecutionType();
  Object result;
  try {
    if (!metaHolder.isObservable()) {
      result=CommandExecutor.execute(invokable,executionType,metaHolder);
    }
 else {
      result=executeObservable(invokable,executionType,metaHolder);
    }
  }
 catch (  HystrixBadRequestException e) {
    throw e.getCause() != null ? e.getCause() : e;
  }
catch (  HystrixRuntimeException e) {
    throw hystrixRuntimeExceptionToThrowable(metaHolder,e);
  }
  return result;
}
