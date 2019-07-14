protected Node getNextSibling(Node contextNode){
  Node parentNode=contextNode.jjtGetParent();
  if (parentNode != null) {
    int nextPosition=contextNode.jjtGetChildIndex() + 1;
    if (nextPosition < parentNode.jjtGetNumChildren()) {
      return parentNode.jjtGetChild(nextPosition);
    }
  }
  return null;
}
