@Bean public InServiceTreeInSqlTerm<String> departmentInSqlTerm(DepartmentService departmentService){
  return new InServiceTreeInSqlTerm<>(departmentService,"dept","s_department",false,false);
}
