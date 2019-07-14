@Override @CacheEvict(cacheNames={CacheConstants.MENU_CACHE_NAME,CacheConstants.USER_MENU_CACHE_NAME},allEntries=true) public int updateByPk(String id,MenuEntity entity){
  return super.updateByPk(id,entity);
}
