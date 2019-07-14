@Override public int jjtGetChildIndex(){
  org.w3c.dom.Node parent=node.getParentNode();
  NodeList childNodes=parent.getChildNodes();
  for (int i=0; i < childNodes.getLength(); i++) {
    if (node == childNodes.item(i)) {
      return i;
    }
  }
  throw new IllegalStateException("This node is not a child of its parent: " + node);
}
