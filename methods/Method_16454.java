static Permission.DataAccessPredicate<ScopeDataAccessConfig> personScope(String action,String type){
  return Permission.scope(action,PERSON_SCOPE,type);
}
