static Permission.DataAccessPredicate<ScopeDataAccessConfig> orgScope(String action,String type){
  return Permission.scope(action,ORG_SCOPE,type);
}
