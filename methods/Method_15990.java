@Override @CacheEvict(allEntries=true) public E deleteByPk(PK pk){
  return super.deleteByPk(pk);
}
