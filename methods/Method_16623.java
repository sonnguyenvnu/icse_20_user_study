@Bean public UserInSqlTerm personInDepartmentSqlTerm(DepartmentService departmentService){
  return new UserInDepartmentSqlTerm(false,false,"person-in-department",departmentService).forPerson();
}
