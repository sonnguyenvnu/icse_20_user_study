private boolean isPrimitiveType(ASTName name){
  ASTType type=getTypeNode(name);
  return type != null && !type.findChildrenOfType(ASTPrimitiveType.class).isEmpty();
}
