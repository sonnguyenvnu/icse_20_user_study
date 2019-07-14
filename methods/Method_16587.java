@Override @Cacheable(key="'person-name:'+#name") public List<PersonEntity> selectByName(String name){
  if (StringUtils.isEmpty(name)) {
    return new ArrayList<>();
  }
  return createQuery().where(PersonEntity.name,name).listNoPaging();
}
