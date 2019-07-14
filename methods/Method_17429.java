private void onHit(Node node){
  if (node.type == QueueType.T1) {
    sizeT1--;
    sizeT2++;
  }
  node.remove();
  node.type=QueueType.T2;
  node.appendToTail(headT2);
  policyStats.recordHit();
}
