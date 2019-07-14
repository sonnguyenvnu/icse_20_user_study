static Permission.DataAccessPredicate<ScopeDataAccessConfig> customOrgScope(String action){
  return orgScope(action,SCOPE_TYPE_CUSTOM);
}
