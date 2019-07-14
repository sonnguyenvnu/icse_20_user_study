/** 
 * Get the children of each element in the set of matched elements,  including text and comment nodes.
 */
public Jerry contents(){
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    for (    Node node : nodes) {
      Node[] contents=node.getChildNodes();
      Collections.addAll(result,contents);
    }
  }
  return new Jerry(this,result);
}
