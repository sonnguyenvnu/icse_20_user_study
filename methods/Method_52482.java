public boolean isVarargs(){
  ASTVariableDeclaratorId astVariableDeclaratorId=(ASTVariableDeclaratorId)node;
  ASTFormalParameter parameter=astVariableDeclaratorId.getFirstParentOfType(ASTFormalParameter.class);
  return parameter != null && parameter.isVarargs();
}
