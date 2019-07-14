@Bean public UserInSqlTerm personInDepartmentParentSqlTerm(DepartmentService departmentService){
  return new UserInDepartmentSqlTerm(false,true,"person-in-department-parent",departmentService).forPerson().forParent();
}
