@Override @Cacheable(key="'group-id-list:'+(#groupId==null?0:#groupId.hashCode())") public List<MenuEntity> getMenuByGroupId(List<String> groupId){
  List<MenuGroupBindEntity> bindEntities=menuGroupBindService.selectByPk(groupId);
  if (bindEntities == null || bindEntities.isEmpty()) {
    return new LinkedList<>();
  }
  return menuService.selectByPk(bindEntities.stream().map(MenuGroupBindEntity::getMenuId).distinct().collect(Collectors.toList()));
}
