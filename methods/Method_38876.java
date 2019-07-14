/** 
 * Removes child node. It works only with direct children, i.e. if provided child node is not a child nothing happens.
 */
public void removeChild(final Node childNode){
  if (childNode.getParentNode() != this) {
    return;
  }
  childNode.detachFromParent();
}
