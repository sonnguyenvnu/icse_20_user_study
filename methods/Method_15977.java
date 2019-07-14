@Override @Transactional(readOnly=true) public List<E> selectParentNode(PK childId){
  assertNotNull(childId);
  E old=selectByPk(childId);
  if (null == old) {
    return new ArrayList<>();
  }
  return createQuery().where().and("path$like$reverse$startWith",old.getPath()).listNoPaging();
}
