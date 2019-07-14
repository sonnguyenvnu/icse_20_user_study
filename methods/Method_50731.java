private boolean parentNotForEach(ASTSoslExpression node){
  return !(node.jjtGetParent() instanceof ASTForEachStatement);
}
