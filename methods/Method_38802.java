/** 
 * Get all preceding siblings of each element in the set of matched  elements, optionally filtered by a selector.
 */
public Jerry prevAll(){
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    for (    Node node : nodes) {
      Node currentSiblingElement=node.getPreviousSiblingElement();
      while (currentSiblingElement != null) {
        result.add(currentSiblingElement);
        currentSiblingElement=currentSiblingElement.getPreviousSiblingElement();
      }
    }
  }
  return new Jerry(this,result);
}
