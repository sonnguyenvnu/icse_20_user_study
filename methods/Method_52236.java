private List<ASTConstructorDeclaration> findAllConstructors(ASTClassOrInterfaceDeclaration node){
  return node.getFirstChildOfType(ASTClassOrInterfaceBody.class).findDescendantsOfType(ASTConstructorDeclaration.class);
}
