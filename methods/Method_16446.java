static Permission.DataAccessPredicate<ScopeDataAccessConfig> districtScope(String action,String type){
  return Permission.scope(action,ORG_SCOPE,type);
}
