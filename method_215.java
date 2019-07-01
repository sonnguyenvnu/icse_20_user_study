@Override public ApplicationEntity _XXXXX_(ApplicationEntity entity){
  entity.ensureDefault();
  if (getBySiteIdAndAppType(entity.getSite().getSiteId(),entity.getDescriptor().getType()) != null) {
    throw new IllegalArgumentException("Duplicated appId: " + entity.getAppId());
  }
  List<ApplicationEntity> entities=new ArrayList<>(1);
  entities.add(entity);
  try {
    queryService.insert(insertSql,entities,new ApplicationEntityToRelation());
  }
 catch (  SQLException e) {
    LOGGER.error("Error to insert ApplicationEntity: {}",entity,e);
  }
  return entity;
}