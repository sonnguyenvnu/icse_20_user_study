public static Node getElementNode(Node base,String name){
  Node node=base.getFirstChild();
  while (node != null) {
    if (node.getNodeType() == Node.ELEMENT_NODE) {
      if (name.equals(node.getNodeName())) {
        return node;
      }
    }
    node=node.getNextSibling();
  }
  return null;
}
