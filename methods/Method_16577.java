@Override @CacheEvict(allEntries=true) public void enable(String id){
  Objects.requireNonNull(id);
  createUpdate().set(DistrictEntity.status,DataStatus.STATUS_ENABLED).where(DistrictEntity.id,id).exec();
  publisher.publishEvent(new ClearPersonCacheEvent());
}
