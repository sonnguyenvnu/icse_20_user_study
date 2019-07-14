@Override public Description matchTry(TryTree tree,VisitorState state){
  if (!JUnitMatchers.TEST_CASE.matches(state.findEnclosing(MethodTree.class),state)) {
    return NO_MATCH;
  }
  List<? extends StatementTree> body=tree.getBlock().getStatements();
  if (body.isEmpty() || tree.getFinallyBlock() != null || tree.getCatches().size() != 1) {
    return NO_MATCH;
  }
  CatchTree catchTree=getOnlyElement(tree.getCatches());
  if (catchTree.getParameter().getType().getKind() == UNION_TYPE) {
    return NO_MATCH;
  }
  if (!FAIL_METHOD.matches(getLast(body),state)) {
    return NO_MATCH;
  }
  List<? extends StatementTree> throwingStatements=body.subList(0,body.size() - 1);
  Iterable<? extends ExpressionTree> failArgs=((MethodInvocationTree)((ExpressionStatementTree)getLast(body)).getExpression()).getArguments();
  Optional<Tree> message=Optional.ofNullable(Iterables.get(failArgs,0,null));
  Optional<Fix> fix=AssertThrowsUtils.tryFailToAssertThrows(tree,throwingStatements,message,state);
  return fix.isPresent() ? describeMatch(tree,fix) : NO_MATCH;
}
