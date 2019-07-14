@Override protected Set<GuardedByExpression> getActual(MethodTree tree,VisitorState state){
  return ImmutableSet.copyOf(HeldLockAnalyzer.ReleasedLockFinder.find(tree.getBody(),state));
}
