@Override @Cacheable(key="'by-department:'+#departmentId.hashCode()") public List<PersonEntity> selectByDepartmentId(List<String> departmentId){
  if (CollectionUtils.isEmpty(departmentId)) {
    return new ArrayList<>();
  }
  return createQuery().where(PersonEntity.id,"person-in-department",departmentId).listNoPaging();
}
