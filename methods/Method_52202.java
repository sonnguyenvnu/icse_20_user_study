private boolean statementsBeforeReturn(VariableNameDeclaration variableDeclaration,ASTReturnStatement returnStatement){
  if (!getProperty(STATEMENT_ORDER_MATTERS)) {
    return false;
  }
  ASTBlockStatement declarationStatement=variableDeclaration.getAccessNodeParent().getFirstParentOfType(ASTBlockStatement.class);
  ASTBlockStatement returnBlockStatement=returnStatement.getFirstParentOfType(ASTBlockStatement.class);
  if (declarationStatement.jjtGetParent() == returnBlockStatement.jjtGetParent()) {
    return returnBlockStatement.jjtGetChildIndex() - declarationStatement.jjtGetChildIndex() > 1;
  }
  return false;
}
