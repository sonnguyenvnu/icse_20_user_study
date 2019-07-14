private boolean hasImports(ASTCompilationUnit cu,String className){
  List<ASTImportDeclaration> imports=cu.findDescendantsOfType(ASTImportDeclaration.class);
  for (  ASTImportDeclaration importDeclaration : imports) {
    ASTName name=importDeclaration.getFirstChildOfType(ASTName.class);
    if (name != null && name.hasImageEqualTo(className)) {
      return true;
    }
  }
  return false;
}
