@Override @CacheEvict(allEntries=true) public int updateByPk(MenuGroupEntity entity){
  int size=super.updateByPk(entity);
  List<MenuGroupBindEntity> bindEntities=entity.getBindInfo();
  if (bindEntities != null && !bindEntities.isEmpty()) {
    TreeSupportEntity.forEach(bindEntities,bindEntity -> {
      bindEntity.setGroupId(entity.getId());
    }
);
    menuGroupBindService.deleteByGroupId(entity.getId());
    menuGroupBindService.insertBatch(bindEntities);
  }
  return size;
}
