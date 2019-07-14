private boolean hasName(Node n){
  return n.jjtGetNumChildren() > 0 && n.jjtGetChild(0) instanceof ASTName;
}
