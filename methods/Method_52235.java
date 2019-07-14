private boolean inAnonymousInnerClass(Node node){
  ASTClassOrInterfaceBodyDeclaration parent=node.getFirstParentOfType(ASTClassOrInterfaceBodyDeclaration.class);
  return parent != null && parent.isAnonymousInnerClass();
}
