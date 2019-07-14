/** 
 * Gets the immediate children of each element in the set of matched elements.
 */
public Jerry children(){
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    for (    Node node : nodes) {
      Node[] children=node.getChildElements();
      Collections.addAll(result,children);
    }
  }
  return new Jerry(this,result);
}
