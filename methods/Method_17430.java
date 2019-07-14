private void onHitB1(Node node){
  p=Math.min(maximumSize,p + Math.max(sizeB2 / sizeB1,1));
  evict(node);
  sizeT2++;
  sizeB1--;
  node.remove();
  node.type=QueueType.T2;
  node.appendToTail(headT2);
  policyStats.recordMiss();
}
