@Override public boolean enable(String userId){
  if (!StringUtils.hasLength(userId)) {
    return false;
  }
  return createUpdate(getDao()).set(UserEntity.status,DataStatus.STATUS_ENABLED).where(GenericEntity.id,userId).exec() > 0;
}
