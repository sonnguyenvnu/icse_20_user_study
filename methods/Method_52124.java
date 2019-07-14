/** 
 * Find out whether the variable is used in an outer class
 */
private boolean usedInOuterClass(ASTClassOrInterfaceDeclaration node,NameDeclaration decl){
  List<ASTClassOrInterfaceDeclaration> outerClasses=node.getParentsOfType(ASTClassOrInterfaceDeclaration.class);
  for (  ASTClassOrInterfaceDeclaration outerClass : outerClasses) {
    ASTClassOrInterfaceBody classOrInterfaceBody=outerClass.getFirstChildOfType(ASTClassOrInterfaceBody.class);
    if (usedInOuter(decl,classOrInterfaceBody)) {
      return true;
    }
  }
  return false;
}
