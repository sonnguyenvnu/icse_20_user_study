@Override @CacheEvict(allEntries=true) public PersonEntity deleteByPk(String id){
  personPositionDao.deleteByPersonId(id);
  return super.deleteByPk(id);
}
