@Override @Cacheable(key="'user:'+#userId+'.'+#key") public List<UserSettingEntity> selectByUser(String userId,String key){
  Objects.requireNonNull(userId);
  Objects.requireNonNull(key);
  return createQuery().where("userId",userId).and("key",key).listNoPaging();
}
