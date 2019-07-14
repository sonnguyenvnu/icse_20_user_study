@Override public boolean supports(ASTUserClassOrInterface<?> node){
  return node.getTypeKind() == TypeKind.CLASS;
}
