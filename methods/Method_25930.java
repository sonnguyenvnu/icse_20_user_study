/** 
 * Checks that the statement, after unwrapping any braces, consists of a single call to a  {@code fail*} method.
 */
private static boolean isCallToFail(StatementTree then,VisitorState state){
  while (then.getKind() == BLOCK) {
    List<? extends StatementTree> statements=((BlockTree)then).getStatements();
    if (statements.size() != 1) {
      return false;
    }
    then=getOnlyElement(statements);
  }
  if (then.getKind() != EXPRESSION_STATEMENT) {
    return false;
  }
  ExpressionTree thenExpr=((ExpressionStatementTree)then).getExpression();
  if (thenExpr.getKind() != METHOD_INVOCATION) {
    return false;
  }
  MethodInvocationTree thenCall=(MethodInvocationTree)thenExpr;
  ExpressionTree methodSelect=thenCall.getMethodSelect();
  if (methodSelect.getKind() != IDENTIFIER) {
    return false;
  }
  return FAIL_METHOD.matches(methodSelect,state);
}
