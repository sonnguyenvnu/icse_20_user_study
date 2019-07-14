@Override @CacheEvict(cacheNames={CacheConstants.MENU_CACHE_NAME,CacheConstants.USER_MENU_CACHE_NAME},allEntries=true) public int updateBatch(Collection<MenuEntity> data){
  return super.updateBatch(data);
}
