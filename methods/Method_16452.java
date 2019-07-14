static Permission.DataAccessPredicate<ScopeDataAccessConfig> childrenDepartmentScope(String action){
  return departmentScope(action,SCOPE_TYPE_CHILDREN);
}
