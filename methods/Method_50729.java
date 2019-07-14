private boolean insideLoop(ASTSoqlExpression node){
  Node n=node.jjtGetParent();
  while (n != null) {
    if (n instanceof ASTDoLoopStatement || n instanceof ASTWhileLoopStatement || n instanceof ASTForLoopStatement || n instanceof ASTForEachStatement) {
      return true;
    }
    n=n.jjtGetParent();
  }
  return false;
}
