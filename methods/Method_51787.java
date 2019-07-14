@Override public List<ASTAnyTypeBodyDeclaration> getDeclarations(){
  return getFirstChildOfType(ASTAnnotationTypeBody.class).findChildrenOfType(ASTAnyTypeBodyDeclaration.class);
}
