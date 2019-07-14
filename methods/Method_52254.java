private boolean hasOneBlockStmt(Node node){
  return node.jjtGetChild(0) instanceof ASTBlock && node.jjtGetChild(0).jjtGetNumChildren() == 1 && terminatesInBooleanLiteral(node.jjtGetChild(0).jjtGetChild(0));
}
