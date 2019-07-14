private boolean isJunit3Test(ASTMethodDeclaration node){
  if (!node.getMethodName().startsWith("test")) {
    return false;
  }
  Node parent=node.getFirstParentOfAnyType(ASTEnumConstant.class,ASTAllocationExpression.class,ASTAnyTypeDeclaration.class);
  if (!(parent instanceof ASTClassOrInterfaceDeclaration) || ((ASTClassOrInterfaceDeclaration)parent).isInterface()) {
    return false;
  }
  ASTClassOrInterfaceType superClass=((ASTClassOrInterfaceDeclaration)parent).getSuperClassTypeNode();
  return superClass != null && TypeHelper.isA(superClass,"junit.framework.TestCase");
}
