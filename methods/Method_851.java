/** 
 * check contains POJO
 * @param node compilation unit
 * @return
 */
private boolean hasPojoInJavaFile(ASTCompilationUnit node){
  List<ASTClassOrInterfaceDeclaration> klasses=node.findDescendantsOfType(ASTClassOrInterfaceDeclaration.class);
  for (  ASTClassOrInterfaceDeclaration klass : klasses) {
    if (isPojo(klass)) {
      return true;
    }
  }
  return false;
}
