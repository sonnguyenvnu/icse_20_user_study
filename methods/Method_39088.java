/** 
 * Lookups the scope instance of given scope annotation. If instance does not exist, it will be created, cached and returned.
 */
@SuppressWarnings("unchecked") public <S extends MadvocScope>S defaultOrScopeType(final Class<S> scopeClass){
  if (scopeClass == null) {
    return (S)getOrInitScope(RequestScope.class);
  }
  return (S)getOrInitScope(scopeClass);
}
