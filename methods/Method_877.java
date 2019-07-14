private boolean isAddShutdownHook(ASTAllocationExpression node){
  ASTBlockStatement blockStatement=node.getFirstParentOfType(ASTBlockStatement.class);
  if (blockStatement == null) {
    return false;
  }
  Token token=(Token)blockStatement.jjtGetFirstToken();
  return Runtime.class.getSimpleName().equals(token.image);
}
