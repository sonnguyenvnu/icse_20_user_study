@Override public List<AuthorizationSettingMenuEntity> selectBySettingId(List<String> settingId){
  if (CollectionUtils.isEmpty(settingId)) {
    return new ArrayList<>();
  }
  return createQuery().where().in(AuthorizationSettingMenuEntity.settingId,settingId).listNoPaging();
}
