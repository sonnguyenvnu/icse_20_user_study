private void analyze(final VisitorState state){
  HeldLockAnalyzer.analyze(state,(  ExpressionTree tree,  GuardedByExpression guard,  HeldLockSet live) -> report(GuardedByChecker.this.checkGuardedAccess(tree,guard,live,state),state),this::isSuppressed);
}
