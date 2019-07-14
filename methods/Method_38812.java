/** 
 * Sets one or more attributes for the set of matched elements.
 */
public Jerry attr(final String name,final String value){
  if (nodes.length == 0) {
    return this;
  }
  for (  Node node : nodes) {
    node.setAttribute(name,value);
  }
  return this;
}
