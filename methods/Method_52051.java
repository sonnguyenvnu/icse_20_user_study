private boolean isTestNgClass(ASTCompilationUnit node){
  List<ASTImportDeclaration> imports=node.findDescendantsOfType(ASTImportDeclaration.class);
  for (  ASTImportDeclaration i : imports) {
    if (i.getImportedName() != null && i.getImportedName().startsWith("org.testng")) {
      return true;
    }
  }
  return false;
}
