private String getNodeName(Node node){
  if (node instanceof ASTMethodDeclaration) {
    return ((ASTMethodDeclaration)node).getMethodName();
  }
 else   if (node instanceof ASTMethodOrConstructorDeclaration) {
    return ((ASTConstructorDeclaration)node).getQualifiedName().getOperation();
  }
 else   if (node instanceof ASTFieldDeclaration) {
    return ((ASTFieldDeclaration)node).getVariableName();
  }
 else   if (node instanceof ASTResource) {
    return ((ASTResource)node).getVariableDeclaratorId().getImage();
  }
 else {
    return node.getImage();
  }
}
