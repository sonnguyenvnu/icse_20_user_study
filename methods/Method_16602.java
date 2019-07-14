@Override @CacheEvict(allEntries=true) public PositionEntity deleteByPk(String id){
  if (!CollectionUtils.isEmpty(personDao.selectByPositionId(id))) {
    throw new BusinessException("???????,????!");
  }
  publisher.publishEvent(new ClearPersonCacheEvent());
  return super.deleteByPk(id);
}
