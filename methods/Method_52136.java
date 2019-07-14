private ASTClassOrInterfaceType getTypeOfPrimaryPrefix(ASTPrimarySuffix node){
  return node.jjtGetParent().getFirstChildOfType(ASTPrimaryPrefix.class).getFirstDescendantOfType(ASTClassOrInterfaceType.class);
}
