private String getClassName(ASTCompilationUnit node){
  ASTAnyTypeDeclaration classDecl=node.getFirstDescendantOfType(ASTAnyTypeDeclaration.class);
  if (classDecl == null) {
    return null;
  }
  if (node.declarationsAreInDefaultPackage()) {
    return classDecl.getImage();
  }
  importedOnDemand.add(node.getPackageDeclaration().getPackageNameImage());
  return classDecl.getQualifiedName().toString();
}
