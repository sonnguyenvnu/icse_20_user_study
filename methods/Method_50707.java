private boolean isOverriddenMethod(ASTMethod node){
  return node.getModifiers().isOverride();
}
