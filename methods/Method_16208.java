@Override @CacheEvict(allEntries=true) public MenuGroupEntity deleteByPk(String id){
  return super.deleteByPk(id);
}
