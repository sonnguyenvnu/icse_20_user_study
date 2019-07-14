private boolean parentNotReturn(ASTSoslExpression node){
  return !(node.jjtGetParent() instanceof ASTReturnStatement);
}
