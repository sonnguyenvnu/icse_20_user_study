static Permission.DataAccessPredicate<ScopeDataAccessConfig> childrenOrgScope(String action){
  return orgScope(action,SCOPE_TYPE_CHILDREN);
}
