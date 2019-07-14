public static boolean isPojo(ASTClassOrInterfaceDeclaration node){
  return node != null && isPojo(node.getImage());
}
