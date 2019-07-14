@Override @Cacheable(cacheNames=USER_MENU_CACHE_NAME,key="'user-menu-list:'+#userId") public List<UserMenuEntity> getUserMenuAsList(String userId){
  if (null == userId) {
    return new java.util.ArrayList<>();
  }
  UserEntity userEntity=userService.selectByPk(userId);
  if (userEntity == null) {
    return new java.util.ArrayList<>();
  }
  List<AuthorizationSettingEntity> entities=getUserSetting(userId);
  if (entities.isEmpty()) {
    return new java.util.ArrayList<>();
  }
  List<String> settingIdList=entities.stream().map(AuthorizationSettingEntity::getId).collect(Collectors.toList());
  List<AuthorizationSettingMenuEntity> menuEntities=authorizationSettingMenuService.selectBySettingId(settingIdList);
  List<String> menuIdList=menuEntities.stream().map(AuthorizationSettingMenuEntity::getMenuId).distinct().collect(Collectors.toList());
  if (menuIdList.isEmpty()) {
    return new ArrayList<>();
  }
  Map<String,MenuEntity> menuCache=menuService.selectByPk(menuIdList).stream().collect(Collectors.toMap(MenuEntity::getId,Function.identity()));
  List<UserMenuEntity> reBuildMenu=new LinkedList<>();
  for (  MenuEntity menuEntity : menuCache.values()) {
    UserMenuEntity menu=entityFactory.newInstance(UserMenuEntity.class,menuEntity);
    menu.setSortIndex(menuEntity.getSortIndex());
    menu.setLevel(menuEntity.getLevel());
    menu.setId(menuEntity.getId());
    menu.setParentId(menuEntity.getParentId());
    menu.setMenuId(menuEntity.getId());
    reBuildMenu.add(menu);
  }
  Collections.sort(reBuildMenu);
  return reBuildMenu;
}
