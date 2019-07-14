static Permission.DataAccessPredicate<ScopeDataAccessConfig> customDepartmentScope(String action){
  return departmentScope(action,SCOPE_TYPE_CUSTOM);
}
