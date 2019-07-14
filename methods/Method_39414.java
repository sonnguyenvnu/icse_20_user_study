@Override public boolean accept(final Scope referenceScope){
  Class<? extends Scope> refScopeType=referenceScope.getClass();
  if (refScopeType == ProtoScope.class) {
    return true;
  }
  if (refScopeType == SingletonScope.class) {
    return true;
  }
  if (refScopeType == SessionScope.class) {
    return true;
  }
  return false;
}
