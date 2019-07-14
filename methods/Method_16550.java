@Override public DepartmentRelations and(){
  positionQuery.and();
  departmentQuery.and();
  return super.and();
}
