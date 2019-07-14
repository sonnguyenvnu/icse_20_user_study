/** 
 * Records a miss when the hot and cold set are full. 
 */
private void onFullMiss(Node node){
  node.status=Status.HIR_RESIDENT;
  if (residentSize >= maximumSize) {
    evict();
  }
  boolean isInStack=node.isInStack(StackType.S);
  node.moveToTop(StackType.S);
  if (isInStack) {
    node.status=Status.LIR;
    sizeHot++;
    Node bottom=headS.prevS;
    checkState(bottom.status == Status.LIR);
    bottom.status=Status.HIR_RESIDENT;
    bottom.removeFrom(StackType.S);
    bottom.moveToTop(StackType.Q);
    sizeHot--;
    pruneStack();
  }
 else {
    node.moveToTop(StackType.Q);
  }
}
