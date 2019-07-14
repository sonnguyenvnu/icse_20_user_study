private boolean isPropertyAccessor(ASTMethod node){
  return !node.getParentsOfType(ASTProperty.class).isEmpty();
}
