protected boolean doQueryAccess(OwnCreatedDataAccessConfig access,AuthorizingContext context){
  String userId=context.getAuthentication().getUser().getId();
  if (context.getDefinition().getDataAccessDefinition().getPhased() == Phased.before) {
    Entity entity=context.getParamContext().getParams().values().stream().filter(Entity.class::isInstance).map(Entity.class::cast).findAny().orElse(null);
    if (entity == null) {
      logger.warn("try validate query access, but query entity is null or not instance of org.hswebframework.web.commons.entity.Entity");
      return true;
    }
    if (entity instanceof QueryParamEntity) {
      QueryParamEntity queryParamEntity=((QueryParamEntity)entity);
      List<Term> oldParam=queryParamEntity.getTerms();
      queryParamEntity.setTerms(new ArrayList<>());
      queryParamEntity.where(RecordCreationEntity.creatorId,userId).nest().setTerms(oldParam);
    }
 else     if (entity instanceof RecordCreationEntity) {
      ((RecordCreationEntity)entity).setCreatorId(userId);
    }
 else {
      logger.warn("try validate query access,but entity not support, QueryParamEntity and RecordCreationEntity support now!");
    }
  }
 else {
    Object result=InvokeResultUtils.convertRealResult(context.getParamContext().getInvokeResult());
    return matchCreatorId(result,userId);
  }
  return true;
}
