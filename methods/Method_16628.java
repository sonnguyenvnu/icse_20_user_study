@Bean public UserInSqlTerm personNotInDepartmentParentSqlTerm(DepartmentService departmentService){
  return new UserInDepartmentSqlTerm(true,true,"person-not-in-department-parent",departmentService).forPerson().forParent();
}
