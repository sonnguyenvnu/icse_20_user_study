private boolean isLockTypeAndMethod(ASTStatementExpression statementExpression,String methodName){
  ASTName name=statementExpression.getFirstDescendantOfType(ASTName.class);
  if (name == null || name.getType() == null || !Lock.class.isAssignableFrom(name.getType())) {
    return false;
  }
  Token token=(Token)name.jjtGetLastToken();
  return methodName.equals(token.image);
}
