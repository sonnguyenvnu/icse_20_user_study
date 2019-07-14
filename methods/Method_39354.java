/** 
 * Registers new scope. It is not necessary to manually register scopes, since they become registered on first scope resolving. However, it is possible to pre-register some scopes, or to <i>replace</i> one scope type with another. Replacing may be important for testing purposes when using container-depended scopes.
 */
public void registerScope(final Class<? extends Scope> scopeType,final Scope scope){
  scopes.put(scopeType,scope);
}
