@Override public List<ASTAnyTypeBodyDeclaration> getDeclarations(){
  return getFirstChildOfType(ASTEnumBody.class).findChildrenOfType(ASTAnyTypeBodyDeclaration.class);
}
