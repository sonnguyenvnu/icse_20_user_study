@SuppressWarnings("unchecked") protected boolean doRWAccess(FieldScopeDataAccessConfig access,AuthorizingContext context,Object controller){
  Object id=context.getParamContext().<String>getParameter(context.getDefinition().getDataAccessDefinition().getIdParameterName()).orElse(null);
  if (controller instanceof QueryController) {
    QueryService queryService=(QueryService)((QueryController)controller).getService();
    Object oldData=queryService.selectByPk(id);
    if (oldData != null) {
      try {
        Object value=propertyUtilsBean.getProperty(oldData,access.getField());
        return access.getScope().contains(value);
      }
 catch (      Exception e) {
        logger.error("can't read property {}",access.getField(),e);
      }
      return false;
    }
  }
 else {
    logger.warn("controller is not instanceof QueryController");
  }
  return true;
}
