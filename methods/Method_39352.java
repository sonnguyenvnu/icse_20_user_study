/** 
 * Resolves and registers scope from a scope type.
 */
@SuppressWarnings("unchecked") public <S extends Scope>S resolveScope(final Class<S> scopeType){
  S scope=(S)scopes.get(scopeType);
  if (scope == null) {
    try {
      scope=newInternalInstance(scopeType,(PetiteContainer)this);
    }
 catch (    Exception ex) {
      throw new PetiteException("Invalid Petite scope: " + scopeType.getName(),ex);
    }
    registerScope(scopeType,scope);
    scopes.put(scopeType,scope);
  }
  return scope;
}
