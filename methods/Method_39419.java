/** 
 * Allows only singleton scoped beans to be injected into the target singleton bean.
 */
@Override public boolean accept(final Scope referenceScope){
  Class<? extends Scope> refScopeType=referenceScope.getClass();
  if (refScopeType == ProtoScope.class) {
    return true;
  }
  if (refScopeType == SingletonScope.class) {
    return true;
  }
  return false;
}
