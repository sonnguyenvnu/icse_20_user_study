static Permission.DataAccessPredicate<ScopeDataAccessConfig> selfScope(String action){
  return personScope(action,SCOPE_TYPE_ONLY_SELF);
}
