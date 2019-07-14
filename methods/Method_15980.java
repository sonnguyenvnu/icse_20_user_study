protected void applyPath(E entity){
  if (StringUtils.isEmpty(entity.getParentId())) {
    if (entity.getSortIndex() == null) {
      entity.setSortIndex(0L);
    }
    entity.setParentId(createParentIdOnExists());
    entity.setLevel(0);
    entity.setPath(RandomUtil.randomChar(4));
    return;
  }
  if (!StringUtils.isEmpty(entity.getPath())) {
    return;
  }
  TreeSortSupportEntity<PK> parent=selectByPk(entity.getParentId());
  if (null == parent) {
    if (entity.getSortIndex() == null) {
      entity.setSortIndex(0L);
    }
    entity.setParentId(createParentIdOnExists());
    entity.setPath(RandomUtil.randomChar(4));
    entity.setLevel(0);
  }
 else {
    if (entity.getSortIndex() == null && parent.getSortIndex() != null) {
      entity.setSortIndex(parent.getSortIndex() * 10);
    }
    entity.setPath(parent.getPath() + "-" + RandomUtil.randomChar(4));
    entity.setLevel(entity.getPath().split("[-]").length);
  }
}
