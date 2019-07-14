@Override protected String getOperationScope(DepartmentAttachEntity entity){
  return entity.getDepartmentId();
}
