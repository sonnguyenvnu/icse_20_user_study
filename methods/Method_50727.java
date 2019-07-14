private boolean parentNotReturn(ASTSoqlExpression node){
  return !(node.jjtGetParent() instanceof ASTReturnStatement);
}
