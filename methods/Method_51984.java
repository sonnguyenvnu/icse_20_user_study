@Override public boolean supports(ASTAnyTypeDeclaration node){
  return node.getTypeKind() == TypeKind.CLASS;
}
