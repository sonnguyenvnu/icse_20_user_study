private boolean parentNotForEach(ASTSoqlExpression node){
  return !(node.jjtGetParent() instanceof ASTForEachStatement);
}
