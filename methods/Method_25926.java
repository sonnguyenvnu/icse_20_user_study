private static ImmutableList<ExpressionTree> findActualAndExpected(ExpressionTree condition,VisitorState state){
switch (condition.getKind()) {
case LOGICAL_COMPLEMENT:
    return findActualAndExpectedForPossibleEqualsCall(stripParentheses(((UnaryTree)condition).getExpression()),state);
case NOT_EQUAL_TO:
  return findActualAndExpectedForBinaryOp((BinaryTree)condition,state);
default :
return null;
}
}
