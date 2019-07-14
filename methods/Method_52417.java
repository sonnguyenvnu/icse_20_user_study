@Override public void addDeclaration(NameDeclaration declaration){
  checkForDuplicatedNameDeclaration(declaration);
  super.addDeclaration(declaration);
}
