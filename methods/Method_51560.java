private static boolean isElementNode(Node node,String name){
  return node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(name);
}
