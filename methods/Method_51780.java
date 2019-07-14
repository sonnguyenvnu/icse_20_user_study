@Override public JavaNode getDeclarationNode(){
  if (jjtGetNumChildren() == 0) {
    return null;
  }
  AccessNode node=getFirstChildOfType(AccessNode.class);
  if (node == null) {
    return getFirstChildOfType(ASTInitializer.class);
  }
  return (JavaNode)node;
}
