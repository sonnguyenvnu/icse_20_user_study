private boolean isLockStatementExpression(ASTStatementExpression statementExpression){
  return isLockTypeAndMethod(statementExpression,LOCK_NAME);
}
