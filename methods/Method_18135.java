/** 
 * Continually walks the node hierarchy until a node returns a non inherited layout direction 
 */
@Override public YogaDirection recursivelyResolveLayoutDirection(){
  YogaNode yogaNode=mYogaNode;
  while (yogaNode != null && yogaNode.getLayoutDirection() == YogaDirection.INHERIT) {
    yogaNode=yogaNode.getOwner();
  }
  return yogaNode == null ? YogaDirection.INHERIT : yogaNode.getLayoutDirection();
}
