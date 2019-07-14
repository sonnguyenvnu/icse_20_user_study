private boolean isMethodCall(Node node){
  if (node.jjtGetParent() instanceof ASTPrimaryPrefix && node.getNthParent(2) instanceof ASTPrimaryExpression) {
    Node primaryPrefix=node.jjtGetParent();
    Node expression=primaryPrefix.jjtGetParent();
    boolean hasNextSibling=expression.jjtGetNumChildren() > primaryPrefix.jjtGetChildIndex() + 1;
    if (hasNextSibling) {
      Node nextSibling=expression.jjtGetChild(primaryPrefix.jjtGetChildIndex() + 1);
      if (nextSibling instanceof ASTPrimarySuffix) {
        return true;
      }
    }
  }
  return false;
}
