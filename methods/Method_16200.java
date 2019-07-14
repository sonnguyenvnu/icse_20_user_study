@Override public Authentication initUserAuthorization(String userId){
  if (null == userId) {
    return null;
  }
  UserEntity userEntity=userService.selectByPk(userId);
  if (userEntity == null) {
    return null;
  }
  SimpleAuthentication authentication=new SimpleAuthentication();
  authentication.setUser(SimpleUser.builder().id(userId).username(userEntity.getUsername()).name(userEntity.getName()).type("default").build());
  authentication.setRoles(userService.getUserRole(userId).stream().map(role -> new SimpleRole(role.getId(),role.getName())).collect(Collectors.toList()));
  List<String> settingIdList=getUserSetting(userId).stream().map(AuthorizationSettingEntity::getId).collect(Collectors.toList());
  if (settingIdList.isEmpty()) {
    authentication.setPermissions(new ArrayList<>());
    return authentication;
  }
  List<AuthorizationSettingDetailEntity> detailList=DefaultDSLQueryService.createQuery(authorizationSettingDetailDao).where(status,STATE_OK).and().in(settingId,settingIdList).listNoPaging();
  authentication.setPermissions(initPermission(detailList));
  return authentication;
}
