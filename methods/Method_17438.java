@SuppressWarnings("PMD.ConfusingTernary") private void demote(){
  policyStats.recordEviction();
  for (; ; ) {
    policyStats.recordOperation();
    if (sizeT1 >= Math.max(1,p)) {
      Node candidate=headT1.next;
      if (!candidate.marked) {
        candidate.remove();
        sizeT1--;
        candidate.appendToTail(headB1);
        candidate.type=QueueType.B1;
        sizeB1++;
        return;
      }
 else {
        candidate.marked=false;
        candidate.remove();
        sizeT1--;
        candidate.appendToTail(headT2);
        candidate.type=QueueType.T2;
        sizeT2++;
      }
    }
 else {
      Node candidate=headT2.next;
      if (!candidate.marked) {
        candidate.remove();
        sizeT2--;
        candidate.appendToTail(headB2);
        candidate.type=QueueType.B2;
        sizeB2++;
        return;
      }
 else {
        candidate.marked=false;
        candidate.remove();
        candidate.appendToTail(headT2);
        candidate.type=QueueType.T2;
      }
    }
  }
}
