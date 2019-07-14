/** 
 * Matches comparisons to null (e.g.  {@code foo == null}) and returns the expression being tested.
 */
private static ExpressionTree getNullCheckedExpression(ExpressionTree condition){
  condition=stripParentheses(condition);
  if (!(condition instanceof BinaryTree)) {
    return null;
  }
  BinaryTree bin=(BinaryTree)condition;
  ExpressionTree other;
  if (bin.getLeftOperand().getKind() == Kind.NULL_LITERAL) {
    other=bin.getRightOperand();
  }
 else   if (bin.getRightOperand().getKind() == Kind.NULL_LITERAL) {
    other=bin.getLeftOperand();
  }
 else {
    return null;
  }
  return other;
}
