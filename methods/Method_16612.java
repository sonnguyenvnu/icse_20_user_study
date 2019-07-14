@Bean public InServiceTreeInSqlTerm<String> departmentNotInSqlTerm(DepartmentService departmentService){
  return new InServiceTreeInSqlTerm<>(departmentService,"dept","s_department",true,false);
}
