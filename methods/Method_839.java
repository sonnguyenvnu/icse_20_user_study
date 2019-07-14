/** 
 * If the outer class wasn't found then we'll get in here
 */
private void populateImports(ASTCompilationUnit node){
  List<ASTImportDeclaration> theImportDeclarations=node.findChildrenOfType(ASTImportDeclaration.class);
  importedClasses.putAll(JAVA_LANG);
  for (  ASTImportDeclaration anImportDeclaration : theImportDeclarations) {
    String strPackage=anImportDeclaration.getPackageName();
    if (anImportDeclaration.isImportOnDemand()) {
      importedOnDemand.add(strPackage);
    }
 else     if (!anImportDeclaration.isImportOnDemand()) {
      String strName=anImportDeclaration.getImportedName();
      importedClasses.put(strName,strName);
      importedClasses.put(strName.substring(strPackage.length() + 1),strName);
    }
  }
}
