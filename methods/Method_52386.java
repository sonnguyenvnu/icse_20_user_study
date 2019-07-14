private boolean isAppendingStringLiteral(Node node){
  Node n=node;
  while (n.jjtGetNumChildren() != 0 && !(n instanceof ASTLiteral)) {
    n=n.jjtGetChild(0);
  }
  return n instanceof ASTLiteral;
}
