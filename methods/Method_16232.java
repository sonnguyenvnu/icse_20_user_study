@Override protected boolean dataExisted(UserSettingEntity entity){
  UserSettingEntity old=createQuery().where(entity::getUserId).and(entity::getKey).and(entity::getSettingId).single();
  if (old != null) {
    entity.setId(old.getId());
    return true;
  }
  return false;
}
