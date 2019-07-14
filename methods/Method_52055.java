private boolean isJUnit3Class(ASTCompilationUnit node){
  ASTClassOrInterfaceDeclaration cid=node.getFirstDescendantOfType(ASTClassOrInterfaceDeclaration.class);
  if (cid == null) {
    return false;
  }
  if (node.getType() != null && TypeHelper.isA(node,JUNIT3_CLASS_NAME)) {
    return true;
  }
 else   if (node.getType() == null) {
    ASTExtendsList extendsList=cid.getFirstChildOfType(ASTExtendsList.class);
    if (extendsList == null) {
      return false;
    }
    if (((ASTClassOrInterfaceType)extendsList.jjtGetChild(0)).getImage().endsWith("TestCase")) {
      return true;
    }
    String className=cid.getImage();
    return className.endsWith("Test");
  }
 else   if (hasImports(node,JUNIT3_CLASS_NAME)) {
    return cid.getImage().endsWith("Test");
  }
  return false;
}
