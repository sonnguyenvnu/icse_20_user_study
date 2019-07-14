@Override public CompletableSource requestScope() throws Exception {
  return LifecycleScopes.resolveScopeFromLifecycle(this);
}
