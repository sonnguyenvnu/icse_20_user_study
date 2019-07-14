/** 
 * Finds the first child node with given node name.
 */
public Node findChildNodeWithName(final String name){
  if (childNodes == null) {
    return null;
  }
  for (  final Node childNode : childNodes) {
    if (childNode.getNodeName().equals(name)) {
      return childNode;
    }
  }
  return null;
}
