protected boolean doQueryAccess(ScopeByUserDataAccessConfig config,AuthorizingContext context){
  PersonnelAuthentication authentication=PersonnelAuthentication.current().orElseThrow(AccessDenyException::new);
  ScopeInfo scopeInfo=getScope(config,authentication);
  if (scopeInfo.isEmpty()) {
    return false;
  }
  ControllerCache controllerCache=getControllerCache(config,context);
  if (context.getDefinition().getDataAccessDefinition().getPhased() == Phased.after) {
    Object result=context.getParamContext().getInvokeResult();
    if (result == null) {
      return true;
    }
    result=getRealResult(result);
    Predicate<Object> predicate=(o) -> {
      String value=controllerCache.targetIdGetter.apply(o);
      if (value == null) {
        return true;
      }
      log.debug("????????[{}],scope:{},target:{}",config.getScopeTypeName(),scopeInfo.scope,value);
      return scopeInfo.allScope.contains(value);
    }
;
    if (result instanceof Collection) {
      Collection<?> res=((Collection)result);
      if (res.isEmpty()) {
        return true;
      }
      return res.stream().allMatch(predicate);
    }
 else {
      return predicate.test(result);
    }
  }
  Entity entity=context.getParamContext().getParams().values().stream().filter(Entity.class::isInstance).map(Entity.class::cast).findAny().orElse(null);
  if (entity instanceof QueryParamEntity) {
    QueryParamEntity param=((QueryParamEntity)entity);
    param.toNestQuery(query -> {
      log.debug("??????????[{}],??:{}",config.getScopeTypeName(),scopeInfo.scope);
      controllerCache.queryConsumer.accept(query,scopeInfo);
    }
);
  }
 else {
    log.debug("??[{}]?????????[QueryParamEntity],??????????!",context.getParamContext().getMethod());
  }
  return true;
}
