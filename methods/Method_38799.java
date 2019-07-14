/** 
 * Gets the siblings of each element in the set of matched elements.
 */
public Jerry siblings(){
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    for (    Node node : nodes) {
      Node[] allElements=node.getParentNode().getChildElements();
      for (      Node sibling : allElements) {
        if (sibling != node) {
          result.add(sibling);
        }
      }
    }
  }
  return new Jerry(this,result);
}
