@Override public Description matchMethod(MethodTree tree,final VisitorState state){
  ImmutableList<String> lockExpressions=getLockExpressions(tree);
  if (lockExpressions.isEmpty()) {
    return Description.NO_MATCH;
  }
  Optional<ImmutableSet<GuardedByExpression>> expected=parseLockExpressions(lockExpressions,tree,state);
  if (!expected.isPresent()) {
    return buildDescription(tree).setMessage("Could not resolve lock expression.").build();
  }
  Set<GuardedByExpression> unwanted=getUnwanted(tree,state);
  SetView<GuardedByExpression> mishandled=Sets.intersection(expected.get(),unwanted);
  if (!mishandled.isEmpty()) {
    String message=buildMessage(formatLockString(mishandled));
    return buildDescription(tree).setMessage(message).build();
  }
  Set<GuardedByExpression> actual=getActual(tree,state);
  SetView<GuardedByExpression> unhandled=Sets.difference(expected.get(),actual);
  if (!unhandled.isEmpty()) {
    String message=buildMessage(formatLockString(unhandled));
    return buildDescription(tree).setMessage(message).build();
  }
  return Description.NO_MATCH;
}
