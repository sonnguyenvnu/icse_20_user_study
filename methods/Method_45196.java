private void trimNode(final Node node){
  NodeList children=node.getChildNodes();
  for (int i=children.getLength() - 1; i >= 0; i--) {
    trimChild(node,children.item(i));
  }
}
