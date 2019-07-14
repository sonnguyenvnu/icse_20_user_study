private ControllerCache getControllerCache(ScopeByUserDataAccessConfig config,AuthorizingContext context){
  Class controller=ClassUtils.getUserClass(context.getParamContext().getTarget().getClass());
  CacheKey cacheKey=new CacheKey();
  cacheKey.children=config.isChildren();
  cacheKey.className=controller.getName();
  cacheKey.method=context.getParamContext().getMethod().toString();
  cacheKey.type=config.getScopeType();
  cacheKey.queryController=context.getParamContext().getTarget() instanceof QueryController;
  Class dataAccessEntityType=context.getDefinition().getDataAccessDefinition().getEntityType();
  return cacheMap.computeIfAbsent(cacheKey,key -> {
    ControllerCache controllerCache=new ControllerCache();
    Class entityClass=dataAccessEntityType;
    if (entityClass == Void.class) {
      if (key.queryController) {
        entityClass=org.hswebframework.utils.ClassUtils.getGenericType(controller,0);
      }
    }
    boolean children=key.isChildren();
    if (key.getType().contains("ORG") && OrgAttachEntity.class.isAssignableFrom(entityClass)) {
      String property=getControlProperty(entityClass,OrgAttachEntity::getOrgIdProperty);
      controllerCache.targetIdGetter=createGetter(OrgAttachEntity.class,OrgAttachEntity::getOrgId);
      controllerCache.queryConsumer=(query,scopeInfo) -> {
        query.and(property,children && supportChildSqlTerm() ? "org-child-in" : "in",scopeInfo.scope);
      }
;
    }
 else     if (key.getType().contains("DEPT") && DepartmentAttachEntity.class.isAssignableFrom(entityClass)) {
      String property=getControlProperty(entityClass,DepartmentAttachEntity::getDepartmentIdProperty);
      controllerCache.targetIdGetter=createGetter(DepartmentAttachEntity.class,DepartmentAttachEntity::getDepartmentId);
      controllerCache.queryConsumer=(query,scopeInfo) -> {
        query.and(property,children && supportChildSqlTerm() ? "dept-child-in" : "in",scopeInfo.scope);
      }
;
    }
 else     if (key.getType().contains("POS") && PositionAttachEntity.class.isAssignableFrom(entityClass)) {
      String property=getControlProperty(entityClass,PositionAttachEntity::getPositionIdProperty);
      controllerCache.targetIdGetter=createGetter(PositionAttachEntity.class,PositionAttachEntity::getPositionId);
      controllerCache.queryConsumer=(query,scopeInfo) -> {
        query.and(property,children && supportChildSqlTerm() ? "pos-child-in" : "in",scopeInfo.scope);
      }
;
    }
 else     if (key.getType().contains("DIST") && DistrictAttachEntity.class.isAssignableFrom(entityClass)) {
      String property=getControlProperty(entityClass,DistrictAttachEntity::getDistrictIdProperty);
      controllerCache.targetIdGetter=createGetter(DistrictAttachEntity.class,DistrictAttachEntity::getDistrictId);
      controllerCache.queryConsumer=(query,scopeInfo) -> {
        query.and(property,children && supportChildSqlTerm() ? "dist-child-in" : "in",scopeInfo.scope);
      }
;
    }
 else     if (key.getType().contains("PERSON") && PersonAttachEntity.class.isAssignableFrom(entityClass)) {
      String property=getControlProperty(entityClass,PersonAttachEntity::getPersonIdProperty);
      controllerCache.targetIdGetter=createGetter(PersonAttachEntity.class,PersonAttachEntity::getPersonId);
      controllerCache.queryConsumer=(query,scopeInfo) -> {
        query.and(property,scopeInfo.personTermType,scopeInfo.scope);
      }
;
    }
 else {
      if (UserAttachEntity.class.isAssignableFrom(entityClass)) {
        String property=getControlProperty(entityClass,UserAttachEntity::getUserIdProperty);
        controllerCache.targetIdGetter=createGetter(UserAttachEntity.class,UserAttachEntity::getUserId);
        controllerCache.queryConsumer=(query,scopeInfo) -> {
          query.and(property,scopeInfo.termType,scopeInfo.scope);
        }
;
      }
 else       if (RecordCreationEntity.class.isAssignableFrom(entityClass)) {
        String property=getControlProperty(entityClass,RecordCreationEntity::getCreatorIdProperty);
        controllerCache.targetIdGetter=createGetter(RecordCreationEntity.class,RecordCreationEntity::getCreatorId);
        controllerCache.queryConsumer=(query,scopeInfo) -> {
          query.and(property,scopeInfo.termType,scopeInfo.scope);
        }
;
      }
 else {
        String property=getUserField(entityClass);
        controllerCache.targetIdGetter=createTargetIdGetter(entityClass,property);
        controllerCache.queryConsumer=(query,scopeInfo) -> {
          query.and(property,scopeInfo.termType,scopeInfo.scope);
        }
;
      }
    }
    return controllerCache;
  }
);
}
