/** 
 * Return true if the change is sufficiently different. 
 */
@Override public boolean isAcceptableChange(Changes changes,Tree node,MethodSymbol symbol,VisitorState state){
  int numberOfChanges=changes.changedPairs().size();
  return changes.totalOriginalCost() - changes.totalAssignmentCost() >= threshold * numberOfChanges;
}
