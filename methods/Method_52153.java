private static boolean isParenthesisAroundMatch(Node node){
  if (!(node instanceof ASTPrimaryExpression) || node.jjtGetNumChildren() != 1) {
    return false;
  }
  Node inode=node.jjtGetChild(0);
  if (!(inode instanceof ASTPrimaryPrefix) || inode.jjtGetNumChildren() != 1) {
    return false;
  }
  Node jnode=inode.jjtGetChild(0);
  if (!(jnode instanceof ASTExpression) || jnode.jjtGetNumChildren() != 1) {
    return false;
  }
  Node knode=jnode.jjtGetChild(0);
  return isMatch(knode);
}
