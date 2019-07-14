/** 
 * Removes an attribute from each element in the set of matched elements.
 */
public Jerry removeAttr(final String name){
  if (name == null) {
    return this;
  }
  if (nodes.length == 0) {
    return this;
  }
  for (  Node node : nodes) {
    node.removeAttribute(name);
  }
  return this;
}
