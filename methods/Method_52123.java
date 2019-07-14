private boolean usedInOuterEnum(ASTClassOrInterfaceDeclaration node,NameDeclaration decl){
  List<ASTEnumDeclaration> outerEnums=node.getParentsOfType(ASTEnumDeclaration.class);
  for (  ASTEnumDeclaration outerEnum : outerEnums) {
    ASTEnumBody enumBody=outerEnum.getFirstChildOfType(ASTEnumBody.class);
    if (usedInOuter(decl,enumBody)) {
      return true;
    }
  }
  return false;
}
