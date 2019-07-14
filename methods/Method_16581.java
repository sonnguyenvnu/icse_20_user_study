@Override @CacheEvict(allEntries=true) public void enable(String id){
  Objects.requireNonNull(id);
  createUpdate().set(OrganizationalEntity.status,DataStatus.STATUS_ENABLED).where(OrganizationalEntity.id,id).exec();
  publisher.publishEvent(new ClearPersonCacheEvent());
}
