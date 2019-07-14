private boolean threadFactoryVariable(ASTAllocationExpression node){
  ASTMethodDeclaration methodDeclaration=node.getFirstParentOfType(ASTMethodDeclaration.class);
  if (methodDeclaration == null) {
    return false;
  }
  ASTVariableDeclarator variableDeclarator=methodDeclaration.getFirstParentOfType(ASTVariableDeclarator.class);
  return variableDeclarator != null && variableDeclarator.getType() == ThreadFactory.class;
}
