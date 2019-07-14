@Override @Cacheable(cacheNames=USER_MENU_CACHE_NAME,key="'menu-tree:'+#userId") public List<UserMenuEntity> getUserMenuAsTree(String userId){
  return TreeSupportEntity.list2tree(getUserMenuAsList(userId),UserMenuEntity::setChildren,(Predicate<UserMenuEntity>)menuEntity -> StringUtils.isEmpty(menuEntity.getParentId()) || "-1".equals(menuEntity.getParentId()));
}
