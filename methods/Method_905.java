private boolean isDoubleVariable(ASTPrimaryPrefix node){
  ASTName name=node.getFirstChildOfType(ASTName.class);
  return name != null && Double.class == name.getType();
}
