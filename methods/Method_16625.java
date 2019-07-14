@Bean public UserInSqlTerm personInDepartmentChildSqlTerm(DepartmentService departmentService){
  return new UserInDepartmentSqlTerm(false,true,"person-in-department-child",departmentService).forPerson();
}
