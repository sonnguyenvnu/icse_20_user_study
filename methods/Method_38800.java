/** 
 * Get all following siblings of each element in the set of matched  elements, optionally filtered by a selector.
 */
public Jerry nextAll(){
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    for (    Node node : nodes) {
      Node currentSiblingElement=node.getNextSiblingElement();
      while (currentSiblingElement != null) {
        result.add(currentSiblingElement);
        currentSiblingElement=currentSiblingElement.getNextSiblingElement();
      }
    }
  }
  return new Jerry(this,result);
}
