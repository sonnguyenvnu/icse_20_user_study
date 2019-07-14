public ASTDatatype getTypeNode(){
  if (jjtGetParent() instanceof ASTFormalParameter) {
    return ((ASTFormalParameter)jjtGetParent()).getTypeNode();
  }
 else {
    Node n=jjtGetParent().jjtGetParent();
    if (n instanceof ASTVariableOrConstantDeclaration || n instanceof ASTFieldDeclaration) {
      return n.getFirstChildOfType(ASTDatatype.class);
    }
  }
  throw new RuntimeException("Don't know how to get the type for anything other than ASTLocalVariableDeclaration/ASTFormalParameter/ASTFieldDeclaration");
}
