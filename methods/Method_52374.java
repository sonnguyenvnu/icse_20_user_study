private boolean insideLoop(ASTAllocationExpression node){
  Node n=node.jjtGetParent();
  while (n != null) {
    if (n instanceof ASTDoStatement || n instanceof ASTWhileStatement || n instanceof ASTForStatement) {
      return true;
    }
 else     if (n instanceof ASTForInit) {
      n=n.jjtGetParent();
    }
 else     if (n.jjtGetParent() instanceof ASTForStatement && n.jjtGetParent().jjtGetNumChildren() > 1 && n == n.jjtGetParent().jjtGetChild(1)) {
      n=n.jjtGetParent();
    }
    n=n.jjtGetParent();
  }
  return false;
}
