@Override public PermissionEntity deleteByPk(String id){
  PermissionEntity old=super.deleteByPk(id);
  eventPublisher.publishEvent(new ClearUserAuthorizationCacheEvent(null,true));
  return old;
}
