@SuppressWarnings("all") protected boolean doQueryAccess(FieldFilterDataAccessConfig access,AuthorizingContext context){
  if (context.getDefinition().getDataAccessDefinition().getPhased() == Phased.before) {
    QueryParamEntity entity=context.getParamContext().getParams().values().stream().filter(QueryParamEntity.class::isInstance).map(QueryParamEntity.class::cast).findAny().orElse(null);
    if (entity == null) {
      logger.warn("try validate query access, but query entity is null or not instance of org.hswebframework.web.commons.entity.Entity");
      return true;
    }
    Set<String> denyFields=access.getFields();
    entity.excludes(denyFields.toArray(new String[denyFields.size()]));
  }
 else {
    Object result=InvokeResultUtils.convertRealResult(context.getParamContext().getInvokeResult());
    if (result instanceof Collection) {
      ((Collection)result).forEach(o -> setObjectPropertyNull(o,access.getFields()));
    }
 else {
      setObjectPropertyNull(result,access.getFields());
    }
  }
  return true;
}
