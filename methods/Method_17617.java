@Override public void record(long key){
  policyStats.recordOperation();
  Node node=data.get(key);
  admittor.record(key);
  if (node == null) {
    onMiss(key);
    policyStats.recordMiss();
  }
 else   if (node.status == Status.WINDOW_PROBATION) {
    onWindowProbationHit(node);
    policyStats.recordHit();
  }
 else   if (node.status == Status.WINDOW_PROTECTED) {
    onWindowProtectedHit(node);
    policyStats.recordHit();
  }
 else   if (node.status == Status.MAIN_PROBATION) {
    onMainProbationHit(node);
    policyStats.recordHit();
  }
 else   if (node.status == Status.MAIN_PROTECTED) {
    onMainProtectedHit(node);
    policyStats.recordHit();
  }
 else {
    throw new IllegalStateException();
  }
}
