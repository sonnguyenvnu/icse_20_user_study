@Override public AdminAuthenticationBO authentication(AdminAuthenticationDTO adminAuthenticationDTO){
  AdminDO admin=adminMapper.selectByUsername(adminAuthenticationDTO.getUsername());
  if (admin == null) {
    throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_USERNAME_NOT_REGISTERED.getCode());
  }
  if (!encodePassword(adminAuthenticationDTO.getPassword()).equals(admin.getPassword())) {
    throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_PASSWORD_ERROR.getCode());
  }
  if (CommonStatusEnum.DISABLE.getValue().equals(admin.getStatus())) {
    throw ServiceExceptionUtil.exception(AdminErrorCodeEnum.ADMIN_IS_DISABLE.getCode());
  }
  OAuth2AccessTokenBO accessTokenBO=oauth2Service.createToken(new OAuth2CreateTokenDTO().setUserId(admin.getId()).setUserType(UserTypeEnum.ADMIN.getValue()));
  return AdminConvert.INSTANCE.convert2(admin).setToken(accessTokenBO);
}
