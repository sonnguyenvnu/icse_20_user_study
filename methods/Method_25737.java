@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  ExpressionTree arg=getLast(tree.getArguments());
  if (!(arg instanceof LambdaExpressionTree)) {
    return NO_MATCH;
  }
  Tree body=((LambdaExpressionTree)arg).getBody();
  if (!(body instanceof BlockTree)) {
    return NO_MATCH;
  }
  List<? extends StatementTree> statements=((BlockTree)body).getStatements();
  if (statements.size() <= 1) {
    return NO_MATCH;
  }
  StatementTree last=getLast(statements);
  int startPosition=((JCTree)statements.get(0)).getStartPosition();
  int endPosition=state.getEndPosition(statements.get(statements.size() - 2));
  SuggestedFix.Builder fix=SuggestedFix.builder();
  if (last instanceof ExpressionStatementTree) {
    fix.replace(body,state.getSourceForNode(((ExpressionStatementTree)last).getExpression()));
  }
 else {
    fix.replace(startPosition,endPosition,"");
  }
  fix.prefixWith(state.findEnclosing(StatementTree.class),state.getSourceCode().subSequence(startPosition,endPosition).toString());
  return describeMatch(last,fix.build());
}
