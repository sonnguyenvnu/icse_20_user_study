/** 
 * Evicts while the map exceeds the maximum capacity. 
 */
private void evict(Node candidate){
  if ((sizeT1 >= 1) && (((candidate.type == QueueType.B2) && (sizeT1 == p)) || (sizeT1 > p))) {
    Node victim=headT1.next;
    victim.remove();
    victim.type=QueueType.B1;
    victim.appendToTail(headB1);
    sizeT1--;
    sizeB1++;
  }
 else {
    Node victim=headT2.next;
    victim.remove();
    victim.type=QueueType.B2;
    victim.appendToTail(headB2);
    sizeT2--;
    sizeB2++;
  }
  policyStats.recordEviction();
}
