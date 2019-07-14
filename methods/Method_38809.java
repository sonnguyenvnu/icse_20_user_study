/** 
 * Reduces the set of matched elements to the first in the set.
 */
public Jerry first(){
  List<Node> result=new NodeList(nodes.length);
  if (nodes.length > 0) {
    result.add(nodes[0]);
  }
  return new Jerry(this,result);
}
