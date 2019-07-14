public static boolean requiresParentheses(ExpressionTree expression,VisitorState state){
switch (expression.getKind()) {
case IDENTIFIER:
case MEMBER_SELECT:
case METHOD_INVOCATION:
case ARRAY_ACCESS:
case PARENTHESIZED:
case NEW_CLASS:
case MEMBER_REFERENCE:
case LAMBDA_EXPRESSION:
    return false;
default :
}
if (expression instanceof LiteralTree) {
  if (!isSameType(getType(expression),state.getSymtab().stringType,state)) {
    return false;
  }
  return state.getOffsetTokensForNode(expression).stream().anyMatch(t -> t.kind() == TokenKind.PLUS);
}
if (expression instanceof UnaryTree) {
  return false;
}
return true;
}
