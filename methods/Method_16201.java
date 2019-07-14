@Override public List<Permission> initPermission(String type,String settingFor){
  AuthorizationSettingEntity entity=select(type,settingFor);
  if (entity == null) {
    return new ArrayList<>();
  }
  List<AuthorizationSettingDetailEntity> detailList=DefaultDSLQueryService.createQuery(authorizationSettingDetailDao).where(status,STATE_OK).and().is(settingId,entity.getId()).listNoPaging();
  if (CollectionUtils.isEmpty(detailList)) {
    return new ArrayList<>();
  }
  return initPermission(detailList);
}
