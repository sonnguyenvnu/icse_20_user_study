/** 
 * Gets the value of an attribute for the first element in the set of matched elements. Returns <code>null</code> if set is empty.
 */
public String attr(final String name){
  if (nodes.length == 0) {
    return null;
  }
  if (name == null) {
    return null;
  }
  return nodes[0].getAttribute(name);
}
