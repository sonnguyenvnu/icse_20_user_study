@Override public Object visit(ASTUserClass node,Object data){
  if (Helper.isTestMethodOrClass(node)) {
    return data;
  }
  List<ASTVariableDeclaration> variableDecls=node.findDescendantsOfType(ASTVariableDeclaration.class);
  for (  ASTVariableDeclaration varDecl : variableDecls) {
    findAuthLiterals(varDecl);
  }
  List<ASTField> fieldDecl=node.findDescendantsOfType(ASTField.class);
  for (  ASTField fDecl : fieldDecl) {
    findFieldLiterals(fDecl);
  }
  List<ASTMethodCallExpression> methodCalls=node.findDescendantsOfType(ASTMethodCallExpression.class);
  for (  ASTMethodCallExpression method : methodCalls) {
    flagAuthorizationHeaders(method,data);
  }
  listOfAuthorizationVariables.clear();
  return data;
}
