/** 
 * Index a single node for visitation by rules.
 */
protected void indexNode(Node node){
  List<Node> nodes=nodeNameToNodes.get(node.getXPathNodeName());
  if (nodes != null) {
    nodes.add(node);
  }
}
