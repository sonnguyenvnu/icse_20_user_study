@Override public AuthorizationSettingEntity select(String type,String settingFor){
  tryValidateProperty(type != null,AuthorizationSettingEntity.type,"{can not be null}");
  tryValidateProperty(settingFor != null,AuthorizationSettingEntity.settingFor,"{can not be null}");
  return createQuery().where(AuthorizationSettingEntity.type,type).and(AuthorizationSettingEntity.settingFor,settingFor).single();
}
