private boolean isLockNode(Node node){
  if (!(node instanceof ASTStatementExpression)) {
    return false;
  }
  ASTStatementExpression statementExpression=(ASTStatementExpression)node;
  return isLockStatementExpression(statementExpression);
}
