@Bean @ConditionalOnMissingBean(DepartmentScopeDataAccessHandler.class) public DepartmentScopeDataAccessHandler departmentScopeDataAccessHandler(){
  return new DepartmentScopeDataAccessHandler();
}
