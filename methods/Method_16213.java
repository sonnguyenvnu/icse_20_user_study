@Override @Cacheable(cacheNames=CacheConstants.MENU_CACHE_NAME,key="'ids:'+(#id==null?'0':#id.hashCode())") public List<MenuEntity> selectByPk(List<String> id){
  return super.selectByPk(id);
}
