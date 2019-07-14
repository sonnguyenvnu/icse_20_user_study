@Override @CacheEvict(cacheNames={CacheConstants.USER_AUTH_CACHE_NAME,CacheConstants.USER_MENU_CACHE_NAME},allEntries=true) public int updateByPk(String id,AuthorizationSettingEntity entity){
  int size=super.updateByPk(id,entity);
  if (entity.getMenus() != null) {
    authorizationSettingMenuService.deleteBySettingId(id);
    TreeSupportEntity.forEach(entity.getMenus(),menu -> {
      menu.setStatus(STATUS_ENABLED);
      menu.setSettingId(id);
    }
);
    authorizationSettingMenuService.insertBatch(entity.getMenus());
  }
  if (entity.getDetails() != null) {
    DefaultDSLDeleteService.createDelete(authorizationSettingDetailDao).where(settingId,id).exec();
    for (    AuthorizationSettingDetailEntity detail : entity.getDetails()) {
      detail.setId(getIDGenerator().generate());
      detail.setSettingId(id);
      detail.setStatus(STATUS_ENABLED);
      tryValidate(detail,CreateGroup.class);
      authorizationSettingDetailDao.insert(detail);
    }
  }
  return size;
}
