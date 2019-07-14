@Bean public UserInSqlTerm userInDepartmentSqlTerm(DepartmentService departmentService){
  return new UserInDepartmentSqlTerm(false,false,"user-in-department",departmentService);
}
