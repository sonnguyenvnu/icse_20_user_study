private boolean fourthParentNotReturn(ASTAllocationExpression node){
  return !(node.jjtGetParent().jjtGetParent().jjtGetParent().jjtGetParent() instanceof ASTReturnStatement);
}
