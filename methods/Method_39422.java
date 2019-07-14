/** 
 * Defines allowed referenced scopes that can be injected into the thread-local scoped bean.
 */
@Override public boolean accept(final Scope referenceScope){
  Class<? extends Scope> refScopeType=referenceScope.getClass();
  if (refScopeType == ProtoScope.class) {
    return true;
  }
  if (refScopeType == SingletonScope.class) {
    return true;
  }
  if (refScopeType == ThreadLocalScope.class) {
    return true;
  }
  return false;
}
