protected Node getPreviousSibling(Node contextNode){
  Node parentNode=contextNode.jjtGetParent();
  if (parentNode != null) {
    int prevPosition=contextNode.jjtGetChildIndex() - 1;
    if (prevPosition >= 0) {
      return parentNode.jjtGetChild(prevPosition);
    }
  }
  return null;
}
