private ASTType getTypeNode(ASTName name){
  if (name.getNameDeclaration() instanceof VariableNameDeclaration) {
    VariableNameDeclaration vnd=(VariableNameDeclaration)name.getNameDeclaration();
    if (vnd.getAccessNodeParent() instanceof ASTLocalVariableDeclaration) {
      ASTLocalVariableDeclaration l=(ASTLocalVariableDeclaration)vnd.getAccessNodeParent();
      return l.getTypeNode();
    }
 else     if (vnd.getAccessNodeParent() instanceof ASTFormalParameter) {
      ASTFormalParameter p=(ASTFormalParameter)vnd.getAccessNodeParent();
      return p.getTypeNode();
    }
  }
  return null;
}
