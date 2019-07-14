/** 
 * Reduces the set of matched elements to the last in the set.
 */
public Jerry last(){
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    result.add(nodes[nodes.length - 1]);
  }
  return new Jerry(this,result);
}
