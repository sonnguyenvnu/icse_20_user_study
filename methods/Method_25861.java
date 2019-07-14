private static boolean callsSuperEquals(MethodTree method,VisitorState state){
  if (method.getBody() == null) {
    return false;
  }
  List<? extends Tree> statements=method.getBody().getStatements();
  if (statements.size() != 1) {
    return false;
  }
  Tree statement=getOnlyElement(statements);
  if (!(statement instanceof ReturnTree)) {
    return false;
  }
  ExpressionTree expression=((ReturnTree)statement).getExpression();
  if (expression == null) {
    return false;
  }
  return instanceEqualsInvocation().matches(expression,state);
}
