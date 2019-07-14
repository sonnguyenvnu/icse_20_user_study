/** 
 * Removes elements from the set of matched elements.
 */
public Jerry not(final String cssSelector){
  Node[] notNodes=root().find(cssSelector).nodes;
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    for (    Node node : nodes) {
      if (!ArraysUtil.contains(notNodes,node)) {
        result.add(node);
      }
    }
  }
  return new Jerry(this,result);
}
