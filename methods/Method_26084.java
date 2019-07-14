private boolean tryTreeMatches(TryTree tree,VisitorState state){
  if (!isInClass(tree,state,TEST_CLASS)) {
    return false;
  }
  if (hasToleratedException(tree)) {
    return false;
  }
  boolean assertInCatch=hasAssertInCatch(tree,state);
  if (!hasExpectedException(tree) && !assertInCatch) {
    return false;
  }
  if (hasThrowOrFail(tree,state) || isInInapplicableMethod(tree,state) || returnsInTryCatchOrAfter(tree,state) || isInapplicableExceptionType(tree,state) || isInLoop(state,tree) || hasWhileTrue(tree,state) || hasContinue(tree,state) || hasFinally(tree) || logsInCatch(state,tree)) {
    return false;
  }
  if (assertInCatch && (hasFieldAssignmentInCatch(tree,state) || hasBooleanAssertVariableInCatch(tree,state) || lastTryStatementIsAssert(tree,state))) {
    return false;
  }
  if (tree.getBlock().getStatements().isEmpty()) {
    return false;
  }
  return true;
}
