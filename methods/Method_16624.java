@Bean public UserInSqlTerm personNotInDepartmentSqlTerm(DepartmentService departmentService){
  return new UserInDepartmentSqlTerm(true,false,"person-not-in-department",departmentService).forPerson();
}
