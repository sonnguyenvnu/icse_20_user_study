@Override public void record(long key){
  policyStats.recordOperation();
  Node node=data.get(key);
  if (node == null) {
    onMiss(key);
    policyStats.recordMiss();
  }
 else   if (node.status == Status.WINDOW) {
    onWindowHit(node);
    policyStats.recordHit();
  }
 else   if (node.status == Status.PROBATION) {
    onProbationHit(node);
    policyStats.recordHit();
  }
 else   if (node.status == Status.PROTECTED) {
    onProtectedHit(node);
    policyStats.recordHit();
  }
 else {
    throw new IllegalStateException();
  }
}
