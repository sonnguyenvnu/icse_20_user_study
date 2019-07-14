@Bean public UserInSqlTerm userNotInDepartmentSqlTerm(DepartmentService departmentService){
  return new UserInDepartmentSqlTerm(true,false,"user-not-in-department",departmentService);
}
