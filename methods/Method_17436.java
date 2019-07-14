private void onMiss(long key,Node node){
  policyStats.recordOperation();
  if ((sizeT1 + sizeT2) == maximumSize) {
    demote();
    if (!isGhost(node)) {
      if ((sizeT1 + sizeB1) == maximumSize) {
        Node victim=headB1.next;
        data.remove(victim.key);
        victim.remove();
        sizeB1--;
      }
 else       if ((sizeT1 + sizeT2 + sizeB1 + sizeB2) == (2 * maximumSize)) {
        Node victim=headB2.next;
        data.remove(victim.key);
        victim.remove();
        sizeB2--;
      }
    }
  }
  if (!isGhost(node)) {
    checkState(node == null);
    node=new Node(key);
    node.appendToTail(headT1);
    node.type=QueueType.T1;
    data.put(key,node);
    sizeT1++;
  }
 else   if (node.type == QueueType.B1) {
    p=Math.min(p + Math.max(1,sizeB2 / sizeB1),maximumSize);
    node.remove();
    sizeB1--;
    node.appendToTail(headT2);
    node.type=QueueType.T2;
    sizeT2++;
    node.marked=false;
  }
 else   if (node.type == QueueType.B2) {
    p=Math.max(p - Math.max(1,sizeB1 / sizeB2),0);
    node.remove();
    sizeB2--;
    node.appendToTail(headT2);
    node.type=QueueType.T2;
    sizeT2++;
    node.marked=false;
  }
 else {
    throw new IllegalStateException();
  }
}
