@Override @Transactional(readOnly=true) public List<E> selectAllChildNode(PK parentId){
  assertNotNull(parentId);
  E old=selectByPk(parentId);
  if (null == old) {
    return new ArrayList<>();
  }
  return createQuery().where().like$(TreeSupportEntity.path,old.getPath()).listNoPaging();
}
