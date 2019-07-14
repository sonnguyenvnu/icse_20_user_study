private boolean terminatesInBooleanLiteral(Node node){
  return eachNodeHasOneChild(node) && getLastChild(node) instanceof ASTBooleanLiteral;
}
