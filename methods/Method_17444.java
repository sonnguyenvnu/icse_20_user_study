private void demote(){
  policyStats.recordEviction();
  while (headT2.next.marked) {
    policyStats.recordOperation();
    Node demoted=headT2.next;
    demoted.marked=false;
    demoted.remove();
    sizeT2--;
    demoted.appendToTail(headT1);
    demoted.type=QueueType.T1;
    sizeT1++;
    if (sizeT2 + sizeB2 + sizeT1 - sizeS >= maximumSize) {
      q=Math.min(q + 1,(2 * maximumSize) - sizeT1);
    }
  }
  while ((headT1.next.filter == FilterType.LongTerm) || headT1.next.marked) {
    policyStats.recordOperation();
    Node node=headT1.next;
    if (node.marked) {
      node.moveToTail(headT1);
      node.marked=false;
      if ((sizeT1 >= Math.max(p + 1,sizeB1)) && (node.filter == FilterType.ShortTerm)) {
        node.filter=FilterType.LongTerm;
        sizeS--;
        sizeL++;
      }
    }
 else {
      node.remove();
      node.type=QueueType.T2;
      node.appendToTail(headT2);
      sizeT1--;
      sizeT2++;
      q=Math.max(q - 1,maximumSize - sizeT1);
    }
  }
  if (sizeT1 >= Math.max(1,p)) {
    Node demoted=headT1.next;
    demoted.remove();
    demoted.type=QueueType.B1;
    demoted.appendToTail(headB1);
    sizeT1--;
    sizeB1++;
    sizeS--;
  }
 else {
    Node demoted=headT2.next;
    demoted.remove();
    demoted.type=QueueType.B2;
    demoted.appendToTail(headB2);
    sizeT2--;
    sizeB2++;
    sizeL--;
  }
}
