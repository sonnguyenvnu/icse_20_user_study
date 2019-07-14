private boolean initializedWhenDeclared(VariableNameDeclaration field){
  return field.getAccessNodeParent().hasDescendantOfType(ASTVariableInitializer.class);
}
