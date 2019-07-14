/** 
 * Checks if node is a child of the level node.
 */
private boolean equalsNodeInLevel(AbstractReportNode level,AbstractReportNode node){
  for (int i=0; i < level.getChildCount(); i++) {
    if (level.getChildAt(i).equalsNode(node)) {
      return true;
    }
  }
  return false;
}
