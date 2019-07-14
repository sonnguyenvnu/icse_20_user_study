@Override public void saveSetting(String userId,String key,String value,UserSettingPermission permission){
  UserSettingEntity entity=new UserSettingEntity();
  entity.setUserId(userId);
  entity.setKey("user-setting");
  entity.setSettingId(key);
  entity.setSetting(value);
  entity.setPermission(permission);
  userSettingService.saveOrUpdate(entity);
}
