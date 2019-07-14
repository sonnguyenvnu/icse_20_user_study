/** 
 * Gets the immediately preceding sibling of each element in the set of matched elements.
 */
public Jerry prev(){
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    for (    Node node : nodes) {
      result.add(node.getPreviousSiblingElement());
    }
  }
  return new Jerry(this,result);
}
