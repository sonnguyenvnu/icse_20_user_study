@Override public boolean disable(String userId){
  if (!StringUtils.hasLength(userId)) {
    return false;
  }
  return createUpdate(getDao()).set(UserEntity.status,DataStatus.STATUS_DISABLED).where(GenericEntity.id,userId).exec() > 0;
}
