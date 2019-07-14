private boolean couldBeMethodCall(JavaNode node){
  if (node.getNthParent(2) instanceof ASTPrimaryExpression && node.getNthParent(1) instanceof ASTPrimaryPrefix) {
    int nextSibling=node.jjtGetParent().jjtGetChildIndex() + 1;
    if (node.getNthParent(2).jjtGetNumChildren() > nextSibling) {
      return node.getNthParent(2).jjtGetChild(nextSibling) instanceof ASTPrimarySuffix;
    }
  }
  return false;
}
