@Override @CacheEvict(allEntries=true) public OrganizationalEntity deleteByPk(String id){
  if (DefaultDSLQueryService.createQuery(departmentDao).where(DepartmentEntity.orgId,id).total() > 0) {
    throw new BusinessException("?????????,????");
  }
  publisher.publishEvent(new ClearPersonCacheEvent());
  return super.deleteByPk(id);
}
