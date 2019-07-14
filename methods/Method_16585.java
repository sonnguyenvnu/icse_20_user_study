@Override @CacheEvict(allEntries=true) public int updateByPk(PersonAuthBindEntity authBindEntity){
  if (authBindEntity.getPositionIds() != null) {
    personPositionDao.deleteByPersonId(authBindEntity.getId());
    syncPositionInfo(authBindEntity.getId(),authBindEntity.getPositionIds());
  }
  if (authBindEntity.getPersonUser() != null) {
    syncUserInfo(authBindEntity);
  }
  return this.updateByPk(((PersonEntity)authBindEntity));
}
