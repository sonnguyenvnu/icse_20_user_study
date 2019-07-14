@Override protected Set<GuardedByExpression> getUnwanted(MethodTree tree,VisitorState state){
  return ImmutableSet.copyOf(HeldLockAnalyzer.ReleasedLockFinder.find(tree.getBody(),state));
}
