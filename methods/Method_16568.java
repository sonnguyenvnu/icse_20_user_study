@Override @Cacheable(key="'name:'+#name") public List<DepartmentEntity> selectByName(String name){
  return createQuery().where(DepartmentEntity.name,name).listNoPaging();
}
