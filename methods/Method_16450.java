static Permission.DataAccessPredicate<ScopeDataAccessConfig> departmentScope(String action,String type){
  return Permission.scope(action,DEPARTMENT_SCOPE,type);
}
