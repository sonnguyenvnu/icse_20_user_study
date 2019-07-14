protected void checkForDuplicatedNameDeclaration(NameDeclaration declaration){
  if (declaration instanceof VariableNameDeclaration && getDeclarations().keySet().contains(declaration)) {
    throw new RuntimeException(declaration + " is already in the symbol table");
  }
}
