public void put(RequiresDataAccess dataAccess){
  if (null == dataAccess || dataAccess.ignore()) {
    return;
  }
  if (!"".equals(dataAccess.permission())) {
    permissions.add(dataAccess.permission());
  }
  actions.addAll(Arrays.asList(dataAccess.action()));
  DefaultDataAccessDefinition definition=new DefaultDataAccessDefinition();
  definition.setEntityType(dataAccess.entityType());
  definition.setPhased(dataAccess.phased());
  if (!"".equals(dataAccess.controllerBeanName())) {
    definition.setController(dataAccess.controllerBeanName());
  }
 else   if (DataAccessController.class != dataAccess.controllerClass()) {
    definition.setController(dataAccess.getClass().getName());
  }
  dataAccessDefinition=definition;
  dataAccessControl=true;
}
