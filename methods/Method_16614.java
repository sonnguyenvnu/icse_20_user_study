@Bean public InServiceTreeInSqlTerm<String> departmentNotInSqlTermParent(DepartmentService departmentService){
  return new InServiceTreeInSqlTerm<>(departmentService,"dept","s_department",true,true);
}
