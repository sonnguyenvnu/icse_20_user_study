private static MethodExecutionAction createCacheKeyAction(String method,MetaHolder metaHolder){
  MethodExecutionAction cacheKeyAction=null;
  if (StringUtils.isNotBlank(method)) {
    Method cacheKeyMethod=getDeclaredMethod(metaHolder.getObj().getClass(),method,metaHolder.getMethod().getParameterTypes());
    if (cacheKeyMethod == null) {
      throw new HystrixCachingException("method with name '" + method + "' doesn't exist in class '" + metaHolder.getObj().getClass() + "'");
    }
    if (!cacheKeyMethod.getReturnType().equals(String.class)) {
      throw new HystrixCachingException("return type of cacheKey method must be String. Method: '" + method + "', Class: '" + metaHolder.getObj().getClass() + "'");
    }
    MetaHolder cMetaHolder=MetaHolder.builder().obj(metaHolder.getObj()).method(cacheKeyMethod).args(metaHolder.getArgs()).build();
    cacheKeyAction=new MethodExecutionAction(cMetaHolder.getObj(),cacheKeyMethod,cMetaHolder.getArgs(),cMetaHolder);
  }
  return cacheKeyAction;
}
