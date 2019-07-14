private boolean isNotAnnotated(VariableNameDeclaration variableDeclaration){
  AccessNode accessNodeParent=variableDeclaration.getAccessNodeParent();
  return !accessNodeParent.hasDescendantOfType(ASTAnnotation.class);
}
