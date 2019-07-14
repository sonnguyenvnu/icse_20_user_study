private CommandAction createFallbackAction(MetaHolder metaHolder){
  FallbackMethod fallbackMethod=MethodProvider.getInstance().getFallbackMethod(metaHolder.getObj().getClass(),metaHolder.getMethod(),metaHolder.isExtendedFallback());
  fallbackMethod.validateReturnType(metaHolder.getMethod());
  CommandAction fallbackAction=null;
  if (fallbackMethod.isPresent()) {
    Method fMethod=fallbackMethod.getMethod();
    Object[] args=fallbackMethod.isDefault() ? new Object[0] : metaHolder.getArgs();
    if (fallbackMethod.isCommand()) {
      fMethod.setAccessible(true);
      HystrixCommand hystrixCommand=fMethod.getAnnotation(HystrixCommand.class);
      MetaHolder fmMetaHolder=MetaHolder.builder().obj(metaHolder.getObj()).method(fMethod).ajcMethod(getAjcMethod(metaHolder.getObj(),fMethod)).args(args).fallback(true).defaultFallback(fallbackMethod.isDefault()).defaultCollapserKey(metaHolder.getDefaultCollapserKey()).fallbackMethod(fMethod).extendedFallback(fallbackMethod.isExtended()).fallbackExecutionType(fallbackMethod.getExecutionType()).extendedParentFallback(metaHolder.isExtendedFallback()).observable(ExecutionType.OBSERVABLE == fallbackMethod.getExecutionType()).defaultCommandKey(fMethod.getName()).defaultGroupKey(metaHolder.getDefaultGroupKey()).defaultThreadPoolKey(metaHolder.getDefaultThreadPoolKey()).defaultProperties(metaHolder.getDefaultProperties().orNull()).hystrixCollapser(metaHolder.getHystrixCollapser()).observableExecutionMode(hystrixCommand.observableExecutionMode()).hystrixCommand(hystrixCommand).build();
      fallbackAction=new LazyCommandExecutionAction(fmMetaHolder);
    }
 else {
      MetaHolder fmMetaHolder=MetaHolder.builder().obj(metaHolder.getObj()).defaultFallback(fallbackMethod.isDefault()).method(fMethod).fallbackExecutionType(ExecutionType.SYNCHRONOUS).extendedFallback(fallbackMethod.isExtended()).extendedParentFallback(metaHolder.isExtendedFallback()).ajcMethod(null).args(args).build();
      fallbackAction=new MethodExecutionAction(fmMetaHolder.getObj(),fMethod,fmMetaHolder.getArgs(),fmMetaHolder);
    }
  }
  return fallbackAction;
}
