@Override public void record(long key){
  policyStats.recordOperation();
  Node node=data.get(key);
  if (node == null) {
    onMiss(key);
  }
 else   if (node.type == QueueType.B1) {
    onHitB1(node);
  }
 else   if (node.type == QueueType.B2) {
    onHitB2(node);
  }
 else {
    onHit(node);
  }
}
