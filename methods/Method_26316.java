protected Description checkGuardedAccess(Tree tree,GuardedByExpression guard,HeldLockSet locks,VisitorState state){
  if (isRWLock(guard,state)) {
    return NO_MATCH;
  }
  if (locks.allLocks().contains(guard)) {
    return NO_MATCH;
  }
  if (guard.kind() == GuardedByExpression.Kind.ERROR) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage(buildMessage(guard,locks)).build();
}
