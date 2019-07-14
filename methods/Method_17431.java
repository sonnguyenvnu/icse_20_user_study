private void onHitB2(Node node){
  p=Math.max(0,p - Math.max(sizeB1 / sizeB2,1));
  evict(node);
  sizeT2++;
  sizeB2--;
  node.remove();
  node.type=QueueType.T2;
  node.appendToTail(headT2);
  policyStats.recordMiss();
}
