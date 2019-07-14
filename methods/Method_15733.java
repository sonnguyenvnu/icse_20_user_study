@SuppressWarnings("all") protected boolean doQueryAccess(FieldScopeDataAccessConfig access,AuthorizingContext context){
  if (context.getDefinition().getDataAccessDefinition().getPhased() == Phased.before) {
    QueryParamEntity entity=context.getParamContext().getParams().values().stream().filter(QueryParamEntity.class::isInstance).map(QueryParamEntity.class::cast).findAny().orElse(null);
    if (entity == null) {
      logger.warn("try validate query access, but query entity is null or not instance of org.hswebframework.web.commons.entity.Entity");
      return true;
    }
    List<Term> oldParam=entity.getTerms();
    entity.setTerms(new ArrayList<>());
    entity.addTerm(createQueryTerm(access)).nest().setTerms(oldParam);
  }
 else {
    Object result=InvokeResultUtils.convertRealResult(context.getParamContext().getInvokeResult());
    if (result == null) {
      return true;
    }
    if (result instanceof Collection) {
      return ((Collection)result).stream().allMatch(obj -> propertyInScope(obj,access.getField(),access.getScope()));
    }
 else {
      return propertyInScope(result,access.getField(),access.getScope());
    }
  }
  return true;
}
