private Collection<CacheOperation> computeCacheOperations(Method method,Class<?> targetClass){
  if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
    return null;
  }
  Method specificMethod=ClassUtils.getMostSpecificMethod(method,targetClass);
  specificMethod=BridgeMethodResolver.findBridgedMethod(specificMethod);
  Collection<CacheOperation> opDef=findCacheOperations(targetClass,specificMethod);
  if (opDef != null) {
    return opDef;
  }
  opDef=findCacheOperations(specificMethod.getDeclaringClass());
  if (opDef != null && ClassUtils.isUserLevelMethod(method)) {
    return opDef;
  }
  if (specificMethod != method) {
    opDef=findCacheOperations(targetClass,method);
    if (opDef != null) {
      return opDef;
    }
    opDef=findCacheOperations(method.getDeclaringClass());
    if (opDef != null && ClassUtils.isUserLevelMethod(method)) {
      return opDef;
    }
  }
  return null;
}
