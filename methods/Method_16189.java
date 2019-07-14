@Override public int deleteBySettingId(String settingId){
  Objects.requireNonNull(settingId);
  return createDelete().where(AuthorizationSettingMenuEntity.settingId,settingId).exec();
}
