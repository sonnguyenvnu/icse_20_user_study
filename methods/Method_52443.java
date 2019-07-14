private boolean isStandAlonePostfix(Node primaryExpression){
  if (!(primaryExpression instanceof ASTPostfixExpression) || !(primaryExpression.jjtGetParent() instanceof ASTStatementExpression)) {
    return false;
  }
  ASTPrimaryPrefix pf=(ASTPrimaryPrefix)((ASTPrimaryExpression)primaryExpression.jjtGetChild(0)).jjtGetChild(0);
  if (pf.usesThisModifier()) {
    return true;
  }
  return thirdChildHasDottedName(primaryExpression);
}
