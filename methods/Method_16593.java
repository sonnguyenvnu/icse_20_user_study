@Override @Cacheable(key="'by-user-id:'+#userId") public PersonEntity selectByUserId(String userId){
  if (StringUtils.isEmpty(userId)) {
    return null;
  }
  return createQuery().where(PersonEntity.userId,userId).single();
}
