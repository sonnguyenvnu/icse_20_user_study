protected boolean handleRW(ScopeDataAccessConfig access,AuthorizingContext context){
  Object id=context.getParamContext().<String>getParameter(context.getDefinition().getDataAccessDefinition().getIdParameterName()).orElse(null);
  Object controller=context.getParamContext().getTarget();
  Set<String> ids=getTryOperationScope(access);
  String errorMsg;
  if (controller instanceof QueryController) {
    Class entityType=ClassUtils.getGenericType(controller.getClass(),0);
    if (ClassUtils.instanceOf(entityType,getEntityClass())) {
      @SuppressWarnings("unchecked") QueryService<E,Object> queryService=((QueryController<E,Object,Entity>)controller).getService();
      E oldData=queryService.selectByPk(id);
      return !(oldData != null && !ids.contains(getOperationScope(oldData)));
    }
 else {
      errorMsg="GenericType[0] not instance of " + getEntityClass();
    }
  }
 else {
    errorMsg="target not instance of QueryController";
  }
  logger.warn("do handle {} fail,because {}",access.getAction(),errorMsg);
  return defaultSuccessOnError;
}
