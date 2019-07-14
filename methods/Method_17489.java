private void onResidentHir(Node node){
  policyStats.recordHit();
  boolean isInStack=node.isInStack(StackType.S);
  boolean isTop=node.isStackTop(StackType.S);
  node.moveToTop(StackType.S);
  if (isInStack && !isTop) {
    sizeHot++;
    node.status=Status.LIR;
    node.removeFrom(StackType.Q);
    Node bottom=headS.prevS;
    sizeHot--;
    bottom.status=Status.HIR_RESIDENT;
    bottom.removeFrom(StackType.S);
    bottom.moveToTop(StackType.Q);
    pruneStack();
  }
 else {
    node.moveToTop(StackType.Q);
  }
}
