private boolean isBadTernary(ASTConditionalExpression ternary){
  boolean isInitializer=false;
  ASTVariableInitializer variableInitializer=ternary.getFirstParentOfType(ASTVariableInitializer.class);
  if (variableInitializer != null) {
    ASTBlockStatement statement=ternary.getFirstParentOfType(ASTBlockStatement.class);
    isInitializer=statement == variableInitializer.getFirstParentOfType(ASTBlockStatement.class);
  }
  return !(ternary.jjtGetChild(0) instanceof ASTEqualityExpression) && !isInitializer && !(ternary.getNthParent(2) instanceof ASTReturnStatement) && !(ternary.getNthParent(2) instanceof ASTLambdaExpression);
}
