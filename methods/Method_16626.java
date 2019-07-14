@Bean public UserInSqlTerm personNotInDepartmentChildSqlTerm(DepartmentService departmentService){
  return new UserInDepartmentSqlTerm(true,true,"person-not-in-department-child",departmentService).forPerson();
}
