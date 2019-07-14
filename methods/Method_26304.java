private static Optional<ImmutableSet<GuardedByExpression>> parseLockExpressions(List<String> lockExpressions,Tree tree,VisitorState state){
  ImmutableSet.Builder<GuardedByExpression> builder=ImmutableSet.builder();
  for (  String lockExpression : lockExpressions) {
    Optional<GuardedByExpression> guard=GuardedByBinder.bindString(lockExpression,GuardedBySymbolResolver.from(tree,state));
    if (!guard.isPresent()) {
      return Optional.empty();
    }
    builder.add(guard.get());
  }
  return Optional.of(builder.build());
}
