/** 
 * Returns true either if the two nodes have the same Component type or if both don't have a Component.
 */
private static boolean hostIsCompatible(InternalNode node,DiffNode diffNode){
  if (diffNode == null) {
    return false;
  }
  return isSameComponentType(node.getTailComponent(),diffNode.getComponent());
}
