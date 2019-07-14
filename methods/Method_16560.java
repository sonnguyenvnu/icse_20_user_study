@Override public DepartmentRelations department(){
  return new DefaultDepartmentRelations(serviceContext,() -> serviceContext.getDepartmentService().selectByOrgIds(targetIdSupplier.get(),includeChildren,includeParents).stream().map(DepartmentEntity::getId).collect(Collectors.toList()));
}
