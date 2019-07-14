@Override @CacheEvict(allEntries=true) public int updateByPk(String id,PositionEntity entity){
  publisher.publishEvent(new ClearPersonCacheEvent());
  return super.updateByPk(id,entity);
}
