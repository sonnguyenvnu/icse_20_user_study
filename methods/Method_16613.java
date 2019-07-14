@Bean public InServiceTreeInSqlTerm<String> departmentInSqlTermParent(DepartmentService departmentService){
  return new InServiceTreeInSqlTerm<>(departmentService,"dept","s_department",false,true);
}
