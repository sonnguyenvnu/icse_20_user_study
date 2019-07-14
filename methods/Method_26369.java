@Override protected Set<GuardedByExpression> getActual(MethodTree tree,VisitorState state){
  return ImmutableSet.copyOf(HeldLockAnalyzer.AcquiredLockFinder.find(tree.getBody(),state));
}
