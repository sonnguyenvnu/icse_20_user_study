@Override @Cacheable(key="'user:'+#userId+'.'+#key+'.'+#settingId") public UserSettingEntity selectByUser(String userId,String key,String settingId){
  Objects.requireNonNull(userId);
  Objects.requireNonNull(key);
  Objects.requireNonNull(settingId);
  return createQuery().where("userId",userId).and("key",key).and("settingId",settingId).single();
}
