private void setVariableNameIfExists(Node node){
  if (node instanceof ASTFieldDeclaration) {
    variableName=getVariableNames((ASTFieldDeclaration)node);
  }
 else   if (node instanceof ASTLocalVariableDeclaration) {
    variableName=getVariableNames((ASTLocalVariableDeclaration)node);
  }
 else   if (node instanceof ASTVariableDeclarator) {
    variableName=node.jjtGetChild(0).getImage();
  }
 else   if (node instanceof ASTVariableDeclaratorId) {
    variableName=node.getImage();
  }
 else   if (node instanceof ASTFormalParameter) {
    setVariableNameIfExists(node.getFirstChildOfType(ASTVariableDeclaratorId.class));
  }
 else {
    variableName="";
  }
}
