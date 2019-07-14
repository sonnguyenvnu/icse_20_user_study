@Override @CacheEvict(cacheNames={CacheConstants.USER_AUTH_CACHE_NAME,CacheConstants.USER_MENU_CACHE_NAME},allEntries=true) public AuthorizationSettingEntity deleteByPk(String id){
  Objects.requireNonNull(id,"id can not be null");
  authorizationSettingMenuService.deleteBySettingId(id);
  DefaultDSLDeleteService.createDelete(authorizationSettingDetailDao).where(AuthorizationSettingDetailEntity.settingId,id).exec();
  return super.deleteByPk(id);
}
