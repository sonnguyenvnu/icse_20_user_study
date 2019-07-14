private boolean isDoubleLiteral(ASTPrimaryPrefix node){
  ASTLiteral literal=node.getFirstChildOfType(ASTLiteral.class);
  return literal != null && literal.isDoubleLiteral();
}
