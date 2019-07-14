@Override public DepartmentRelations getDepartmentRelations(List<String> departmentIds){
  return new DefaultDepartmentRelations(context,() -> departmentIds);
}
