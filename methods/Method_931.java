public static boolean isGenerated(ASTCompilationUnit compilationUnit){
  List<ASTImportDeclaration> importDeclarationList=compilationUnit.findChildrenOfType(ASTImportDeclaration.class);
  if (importDeclarationList.isEmpty()) {
    return false;
  }
  for (  ASTImportDeclaration importDeclaration : importDeclarationList) {
    if (ANNOTATION_NAME.equals(importDeclaration.getImportedName())) {
      return true;
    }
  }
  return false;
}
