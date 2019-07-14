private boolean fourthParentNotThrow(ASTAllocationExpression node){
  return !(node.jjtGetParent().jjtGetParent().jjtGetParent().jjtGetParent() instanceof ASTThrowStatement);
}
