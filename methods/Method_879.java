private boolean isInNewThreadMethod(ASTAllocationExpression node){
  ASTMethodDeclaration methodDeclaration=node.getFirstParentOfType(ASTMethodDeclaration.class);
  if (methodDeclaration == null) {
    return false;
  }
  if (!returnThread(methodDeclaration)) {
    return false;
  }
  if (!METHOD_NEW_THREAD.equals(methodDeclaration.getMethodName())) {
    return false;
  }
  List<ASTFormalParameter> parameters=methodDeclaration.getFirstDescendantOfType(ASTFormalParameters.class).findChildrenOfType(ASTFormalParameter.class);
  return parameters.size() == 1 && parameters.get(0).getFirstChildOfType(ASTType.class).getType() == Runnable.class;
}
