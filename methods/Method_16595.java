@Override @Cacheable(key="'all-org-id:'+#personId.hashCode()") public List<String> selectAllOrgId(List<String> personId){
  List<String> departmentId=this.selectAllDepartmentId(personId);
  if (CollectionUtils.isEmpty(departmentId)) {
    return new java.util.ArrayList<>();
  }
  return DefaultDSLQueryService.createQuery(departmentDao).where().in(DepartmentEntity.id,departmentId).listNoPaging().stream().map(DepartmentEntity::getOrgId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
}
