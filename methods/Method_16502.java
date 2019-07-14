protected boolean handleQuery(ScopeDataAccessConfig access,AuthorizingContext context){
  Entity entity=context.getParamContext().getParams().values().stream().filter(Entity.class::isInstance).map(Entity.class::cast).findAny().orElse(null);
  if (entity == null) {
    logger.warn("try validate query access, but query entity is null or not instance of org.hswebframework.web.commons.entity.Entity");
    return defaultSuccessOnError;
  }
  Set<String> scope=getTryOperationScope(access);
  if (scope.isEmpty()) {
    logger.warn("try validate query access,but config is empty!");
    return defaultSuccessOnError;
  }
  if (entity instanceof QueryParamEntity) {
    if (logger.isDebugEnabled()) {
      logger.debug("try rebuild query param ...");
    }
    QueryParamEntity queryParamEntity=((QueryParamEntity)entity);
    queryParamEntity.toNestQuery(query -> query.accept(createQueryTerm(scope,context)));
  }
 else {
    logger.warn("try validate query access,but entity not support, QueryParamEntity support now!");
  }
  return true;
}
