@Override public Object visit(ASTClassOrInterfaceType node,Object data){
  if (methodHasOverride(node)) {
    return data;
  }
  Node parent=node.getNthParent(3);
  Class<?> clazzType=node.getType();
  boolean isType=CollectionUtil.isCollectionType(clazzType,false);
  if (isType && (parent instanceof ASTFieldDeclaration || parent instanceof ASTFormalParameter || parent instanceof ASTResultType)) {
    addViolation(data,node,node.getImage());
  }
  return data;
}
