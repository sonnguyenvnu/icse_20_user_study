private Node getNextSibling(Node current){
  if (current.jjtGetParent().jjtGetNumChildren() > current.jjtGetChildIndex() + 1) {
    return current.jjtGetParent().jjtGetChild(current.jjtGetChildIndex() + 1);
  }
  return null;
}
