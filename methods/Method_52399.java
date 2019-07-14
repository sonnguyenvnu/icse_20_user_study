private int getInitialLength(Node node){
  Node block=node.getFirstParentOfType(ASTBlockStatement.class);
  if (block == null) {
    block=node.getFirstParentOfType(ASTFieldDeclaration.class);
    if (block == null) {
      block=node.getFirstParentOfType(ASTFormalParameter.class);
    }
  }
  List<ASTLiteral> literals=block.findDescendantsOfType(ASTLiteral.class);
  if (literals.size() == 1) {
    ASTLiteral literal=literals.get(0);
    String str=literal.getImage();
    if (str != null && isStringOrCharLiteral(literal)) {
      return str.length() - 2;
    }
  }
  return 0;
}
