public boolean isOnRightHandSide(){
  Node node=location.jjtGetParent().jjtGetParent().jjtGetParent();
  return node instanceof ASTExpression && node.jjtGetNumChildren() == 3;
}
