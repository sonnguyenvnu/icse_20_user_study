public AccessNode getAccessNodeParent(){
  if (node.jjtGetParent() instanceof ASTFormalParameter || node.jjtGetParent() instanceof ASTLambdaExpression) {
    return (AccessNode)node.jjtGetParent();
  }
  return (AccessNode)node.jjtGetParent().jjtGetParent();
}
