/** 
 * Cached version of  {@link #inspectClassScopes(Class)}. Use it in runtime when configuration is not known yet.
 */
public ScopeData inspectClassScopesWithCache(final Class actionClass){
  return scopeDataTypeCache.get(actionClass,() -> inspectClassScopes(actionClass));
}
