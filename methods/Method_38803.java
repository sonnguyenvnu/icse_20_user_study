/** 
 * Gets the descendants of each element in the current set of matched elements, filtered by a selector.
 */
public Jerry find(final String cssSelector){
  final List<Node> result=new NodeList();
  if (nodes.length > 0) {
    for (    Node node : nodes) {
      NodeSelector nodeSelector=createNodeSelector(node);
      List<Node> filteredNodes=nodeSelector.select(cssSelector);
      result.addAll(filteredNodes);
    }
  }
  return new Jerry(this,result);
}
