@Override public Object visit(ASTFormalParameters node,Object data){
  if (!checkParameters) {
    return data;
  }
  ASTMethodDeclaration methodDeclaration=node.getFirstParentOfType(ASTMethodDeclaration.class);
  if (!checkNativeMethodParameters && methodDeclaration.isNative()) {
    return data;
  }
  for (  ASTFormalParameter formalParameter : node.findChildrenOfType(ASTFormalParameter.class)) {
    for (    ASTVariableDeclaratorId variableDeclaratorId : formalParameter.findChildrenOfType(ASTVariableDeclaratorId.class)) {
      checkVariableDeclaratorId(parameterPrefixes,parameterSuffixes,false,formalParameter.isFinal(),variableDeclaratorId,data);
    }
  }
  return data;
}
