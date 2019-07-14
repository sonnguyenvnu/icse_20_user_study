@Override @CacheEvict(cacheNames={CacheConstants.MENU_CACHE_NAME,CacheConstants.USER_MENU_CACHE_NAME},allEntries=true) public MenuEntity deleteByPk(String id){
  return super.deleteByPk(id);
}
