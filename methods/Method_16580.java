@Override @CacheEvict(allEntries=true) public void disable(String id){
  Objects.requireNonNull(id);
  createUpdate().set(OrganizationalEntity.status,DataStatus.STATUS_DISABLED).where(OrganizationalEntity.id,id).exec();
  publisher.publishEvent(new ClearPersonCacheEvent());
}
