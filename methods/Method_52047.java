public static boolean importsPackage(ASTCompilationUnit node,String packageName){
  List<ASTImportDeclaration> nodes=node.findChildrenOfType(ASTImportDeclaration.class);
  for (  ASTImportDeclaration n : nodes) {
    if (n.getPackageName().startsWith(packageName)) {
      return true;
    }
  }
  return false;
}
