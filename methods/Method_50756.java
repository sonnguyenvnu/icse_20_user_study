private void collectBenignVariables(ASTUserClass node){
  List<ASTField> fields=node.findDescendantsOfType(ASTField.class);
  for (  ASTField field : fields) {
    if (BOOLEAN.equalsIgnoreCase(field.getType())) {
      whiteListedVariables.add(Helper.getFQVariableName(field));
    }
  }
  List<ASTVariableDeclaration> declarations=node.findDescendantsOfType(ASTVariableDeclaration.class);
  for (  ASTVariableDeclaration decl : declarations) {
    if (BOOLEAN.equalsIgnoreCase(decl.getType())) {
      whiteListedVariables.add(Helper.getFQVariableName(decl));
    }
  }
}
