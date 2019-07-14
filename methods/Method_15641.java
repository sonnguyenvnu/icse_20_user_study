default Set<Object> findScope(Permission.DataAccessPredicate<ScopeDataAccessConfig> predicate){
  return findDataAccess(predicate).map(ScopeDataAccessConfig::getScope).orElseGet(Collections::emptySet);
}
