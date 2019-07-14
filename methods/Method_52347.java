private boolean isReferencingThrowable(ASTPrimaryExpression last){
  ASTName variable=last.getFirstDescendantOfType(ASTName.class);
  if (variable != null && variable.getNameDeclaration() != null && variable.getNameDeclaration() instanceof VariableNameDeclaration) {
    VariableNameDeclaration declaration=(VariableNameDeclaration)variable.getNameDeclaration();
    if (declaration.getType() != null && Throwable.class.isAssignableFrom(declaration.getType())) {
      return true;
    }
    if (declaration.getTypeImage() != null && declaration.getTypeImage().endsWith("Exception")) {
      return true;
    }
  }
  return false;
}
