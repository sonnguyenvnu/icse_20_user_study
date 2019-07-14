@Override public List<ASTAnyTypeBodyDeclaration> getDeclarations(){
  return getFirstChildOfType(ASTClassOrInterfaceBody.class).findChildrenOfType(ASTAnyTypeBodyDeclaration.class);
}
