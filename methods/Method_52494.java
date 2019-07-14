/** 
 * This method can be called on a prefix
 */
private ASTArguments getSuffixMethodArgs(Node node){
  Node prefix=node.jjtGetParent();
  if (prefix instanceof ASTPrimaryPrefix && prefix.jjtGetParent().jjtGetNumChildren() >= 2) {
    return prefix.jjtGetParent().jjtGetChild(1).getFirstChildOfType(ASTArguments.class);
  }
  return null;
}
