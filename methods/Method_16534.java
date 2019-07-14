protected boolean doUpdateAccess(ScopeByUserDataAccessConfig config,AuthorizingContext context){
  Object id=context.getParamContext().<String>getParameter(context.getDefinition().getDataAccessDefinition().getIdParameterName()).orElse(null);
  if (id == null) {
    return true;
  }
  PersonnelAuthentication authentication=PersonnelAuthentication.current().orElse(null);
  if (authentication == null) {
    log.warn("????????????!");
    return false;
  }
  ScopeInfo scopeInfo=getScope(config,authentication);
  if (scopeInfo.isEmpty()) {
    return false;
  }
  Object controller=context.getParamContext().getTarget();
  QueryService<Object,Object> queryService=null;
  if (controller instanceof QueryController) {
    queryService=((QueryController<Object,Object,Entity>)controller).getService();
  }
 else {
    Method getService=ReflectionUtils.findMethod(controller.getClass(),"getService");
    if (getService != null) {
      try {
        Object service=ReflectionUtils.invokeMethod(getService,controller);
        if (service instanceof QueryService) {
          queryService=((QueryService)service);
        }
      }
 catch (      Exception ignore) {
      }
    }
  }
  if (queryService != null) {
    ControllerCache controllerCache=getControllerCache(config,context);
    Object entity=queryService.selectByPk(id);
    if (null != entity) {
      String targetId=controllerCache.targetIdGetter.apply(entity);
      if (targetId == null) {
        return true;
      }
      log.debug("????????,??:{},??:{}",scopeInfo.scope,targetId);
      return scopeInfo.allScope.contains(controllerCache.targetIdGetter.apply(entity));
    }
  }
 else {
    log.debug("Controller????????CURD??,??????????!");
  }
  return true;
}
