private boolean checkForNamingClass(ASTAllocationExpression node){
  ASTClassOrInterfaceDeclaration classOrInterfaceDeclaration=node.getFirstParentOfType(ASTClassOrInterfaceDeclaration.class);
  if (classOrInterfaceDeclaration == null) {
    return false;
  }
  ASTImplementsList implementsList=classOrInterfaceDeclaration.getFirstChildOfType(ASTImplementsList.class);
  if (implementsList == null) {
    return false;
  }
  List<ASTClassOrInterfaceType> interfaceTypes=implementsList.findChildrenOfType(ASTClassOrInterfaceType.class);
  for (  ASTClassOrInterfaceType type : interfaceTypes) {
    if (type.getType() == ThreadFactory.class) {
      return true;
    }
  }
  return false;
}
