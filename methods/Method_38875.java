/** 
 * Removes child node at given index. Returns removed node or <code>null</code> if index is invalid.
 */
public Node removeChild(final int index){
  if (childNodes == null) {
    return null;
  }
  Node node;
  try {
    node=childNodes.get(index);
  }
 catch (  IndexOutOfBoundsException ignore) {
    return null;
  }
  node.detachFromParent();
  return node;
}
