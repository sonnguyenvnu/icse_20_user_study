protected boolean handleAdd(ScopeDataAccessConfig access,AuthorizingContext context){
  Set<String> scopes=getTryOperationScope(access);
  String scope;
  if (scopes.isEmpty()) {
    return true;
  }
 else   if (scopes.size() == 1) {
    scope=scopes.iterator().next();
  }
 else {
    scope=scopes.iterator().next();
    logger.warn("existing many scope :{} , try use config.",scope);
  }
  if (scope != null) {
    context.getParamContext().getParams().values().stream().filter(getEntityClass()::isInstance).map(getEntityClass()::cast).forEach(entity -> applyScopeProperty(entity,scope));
  }
 else {
    logger.warn("scope is null!");
  }
  return defaultSuccessOnError;
}
