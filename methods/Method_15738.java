@SuppressWarnings("unchecked") protected boolean doRWAccess(OwnCreatedDataAccessConfig access,AuthorizingContext context,Object controller){
  Object id=context.getParamContext().<String>getParameter(context.getDefinition().getDataAccessDefinition().getIdParameterName()).orElse(null);
  if (controller instanceof QueryController) {
    Class entityType=ClassUtils.getGenericType(controller.getClass(),0);
    if (ClassUtils.instanceOf(entityType,RecordCreationEntity.class)) {
      QueryService<RecordCreationEntity,Object> queryService=((QueryController<RecordCreationEntity,Object,Entity>)controller).getService();
      RecordCreationEntity oldData=queryService.selectByPk(id);
      if (oldData != null && !context.getAuthentication().getUser().getId().equals(oldData.getCreatorId())) {
        return false;
      }
    }
  }
  return true;
}
