@Override public void record(long key){
  policyStats.recordOperation();
  Node node=data.get(key);
  admittor.record(key);
  if (node == null) {
    onMiss(key);
    policyStats.recordMiss();
  }
 else {
    onHit(node);
    policyStats.recordHit();
  }
}
