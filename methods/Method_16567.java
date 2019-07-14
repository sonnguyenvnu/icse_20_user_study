@Override @Cacheable(key="'org-ids:'+#orgId==null?0:orgId.hashCode()+'_'+#children+'_'+#parent") public List<DepartmentEntity> selectByOrgIds(List<String> orgId,boolean children,boolean parent){
  if (CollectionUtils.isEmpty(orgId)) {
    return new java.util.ArrayList<>();
  }
  Set<String> allOrgId=new HashSet<>(orgId);
  if (children) {
    allOrgId.addAll(orgId.stream().map(organizationalService::selectAllChildNode).flatMap(Collection::stream).map(OrganizationalEntity::getId).collect(Collectors.toSet()));
  }
  if (parent) {
    allOrgId.addAll(orgId.stream().map(organizationalService::selectParentNode).flatMap(Collection::stream).map(OrganizationalEntity::getId).collect(Collectors.toSet()));
  }
  return createQuery().where().in(DepartmentEntity.orgId,allOrgId).listNoPaging();
}
