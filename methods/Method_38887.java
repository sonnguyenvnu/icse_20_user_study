/** 
 * Returns last child or <code>null</code> if no children exist.
 */
public Node getLastChild(){
  if (childNodes == null) {
    return null;
  }
  if (childNodes.isEmpty()) {
    return null;
  }
  return childNodes.get(getChildNodesCount() - 1);
}
