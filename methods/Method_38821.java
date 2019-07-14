/** 
 * Removes all child nodes of the set of matched elements from the DOM.
 */
public Jerry empty(){
  if (nodes.length == 0) {
    return this;
  }
  for (  Node node : nodes) {
    node.removeAllChilds();
  }
  return this;
}
