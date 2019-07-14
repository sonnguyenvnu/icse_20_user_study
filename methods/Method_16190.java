@Override public List<AuthorizationSettingMenuEntity> selectBySettingId(String settingId){
  Objects.requireNonNull(settingId);
  return createQuery().where(AuthorizationSettingMenuEntity.settingId,settingId).listNoPaging();
}
