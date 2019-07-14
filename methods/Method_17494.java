private void pruneStack(){
  for (; ; ) {
    Node bottom=headS.prevS;
    if ((bottom == headS) || (bottom.status == Status.LIR)) {
      break;
    }
 else     if (bottom.status == Status.HIR_NON_RESIDENT) {
      bottom.removeFrom(StackType.NR);
      data.remove(bottom.key);
    }
    bottom.removeFrom(StackType.S);
    policyStats.recordOperation();
  }
  Node node=headNR.prevNR;
  while (sizeNR > maximumNonResidentSize) {
    policyStats.recordOperation();
    Node removed=node;
    node=node.prevNR;
    removed.removeFrom(StackType.NR);
    removed.removeFrom(StackType.S);
    data.remove(removed.key);
  }
}
