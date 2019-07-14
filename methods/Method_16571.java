@Override @CacheEvict(allEntries=true) public int updateByPk(String id,DistrictEntity entity){
  publisher.publishEvent(new ClearPersonCacheEvent());
  return super.updateByPk(id,entity);
}
