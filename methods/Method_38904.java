protected void walk(final Node rootNode,final NodeFilter nodeFilter,final List<Node> result){
  int childCount=rootNode.getChildNodesCount();
  for (int i=0; i < childCount; i++) {
    Node node=rootNode.getChild(i);
    if (nodeFilter.accept(node)) {
      result.add(node);
    }
    walk(node,nodeFilter,result);
  }
}
