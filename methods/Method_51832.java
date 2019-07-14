public boolean isInterfaceMember(){
  if (getNthParent(2) instanceof ASTEnumBody) {
    return false;
  }
  ASTClassOrInterfaceBody classOrInterfaceBody=getFirstParentOfType(ASTClassOrInterfaceBody.class);
  if (classOrInterfaceBody == null || classOrInterfaceBody.isAnonymousInnerClass()) {
    return false;
  }
  if (classOrInterfaceBody.jjtGetParent() instanceof ASTClassOrInterfaceDeclaration) {
    ASTClassOrInterfaceDeclaration n=(ASTClassOrInterfaceDeclaration)classOrInterfaceBody.jjtGetParent();
    return n.isInterface();
  }
  return false;
}
