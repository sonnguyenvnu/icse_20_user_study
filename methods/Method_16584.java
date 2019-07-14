@Override @CacheEvict(allEntries=true) public String insert(PersonAuthBindEntity authBindEntity){
  authBindEntity.setStatus(DataStatus.STATUS_ENABLED);
  if (authBindEntity.getPersonUser() != null) {
    syncUserInfo(authBindEntity);
  }
  String id=this.insert(((PersonEntity)authBindEntity));
  if (authBindEntity.getPositionIds() != null) {
    syncPositionInfo(id,authBindEntity.getPositionIds());
  }
  return id;
}
