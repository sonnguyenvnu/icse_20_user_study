@Override @CacheEvict(allEntries=true) public void disable(String id){
  Objects.requireNonNull(id);
  createUpdate().set(DistrictEntity.status,DataStatus.STATUS_DISABLED).where(DistrictEntity.id,id).exec();
  publisher.publishEvent(new ClearPersonCacheEvent());
}
