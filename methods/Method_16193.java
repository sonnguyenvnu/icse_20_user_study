@Override @CacheEvict(cacheNames={CacheConstants.USER_AUTH_CACHE_NAME,CacheConstants.USER_MENU_CACHE_NAME},allEntries=true) public String insert(AuthorizationSettingEntity entity){
  tryValidateProperty(select(entity.getType(),entity.getSettingFor()) == null,AuthorizationSettingEntity.settingFor,"???????!");
  entity.setStatus(STATUS_ENABLED);
  String id=super.insert(entity);
  if (entity.getMenus() != null) {
    TreeSupportEntity.forEach(entity.getMenus(),menu -> {
      menu.setStatus(STATUS_ENABLED);
      menu.setSettingId(id);
    }
);
    authorizationSettingMenuService.insertBatch(entity.getMenus());
  }
  if (entity.getDetails() != null) {
    for (    AuthorizationSettingDetailEntity detail : entity.getDetails()) {
      detail.setId(getIDGenerator().generate());
      detail.setSettingId(id);
      detail.setStatus(STATUS_ENABLED);
      tryValidate(detail,CreateGroup.class);
      authorizationSettingDetailDao.insert(detail);
    }
  }
  return id;
}
