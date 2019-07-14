private void onHit(Node node){
  policyStats.recordOperation();
  policyStats.recordHit();
  node.marked=true;
}
