protected List<Node> select(final Node rootNode,final List<CssSelector> selectors){
  List<Node> nodes=new ArrayList<>();
  nodes.add(rootNode);
  for (  CssSelector cssSelector : selectors) {
    List<Node> selectedNodes=new ArrayList<>();
    for (    Node node : nodes) {
      walk(node,cssSelector,selectedNodes);
    }
    List<Node> resultNodes=new ArrayList<>();
    int index=0;
    for (    Node node : selectedNodes) {
      boolean match=filter(selectedNodes,node,cssSelector,index);
      if (match) {
        resultNodes.add(node);
      }
      index++;
    }
    nodes=resultNodes;
  }
  return nodes;
}
