@Override public String insert(PermissionEntity entity){
  entity.setStatus(DataStatus.STATUS_ENABLED);
  return super.insert(entity);
}
