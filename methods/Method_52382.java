private boolean hasInitializer(ASTVariableDeclaratorId node){
  return node.jjtGetParent().hasDescendantOfType(ASTVariableInitializer.class);
}
