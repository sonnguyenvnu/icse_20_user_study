/** 
 * Finds the last table in stack of open elements.
 */
protected Element findLastTable(final Node node){
  Node tableNode=node;
  while (tableNode != null) {
    if (tableNode.getNodeType() == Node.NodeType.ELEMENT) {
      String tableNodeName=tableNode.getNodeName().toLowerCase();
      if (tableNodeName.equals("table")) {
        break;
      }
    }
    tableNode=tableNode.getParentNode();
  }
  return (Element)tableNode;
}
