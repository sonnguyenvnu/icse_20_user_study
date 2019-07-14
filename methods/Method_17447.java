private void onNonResidentHit(Node node){
  policyStats.recordOperation();
  policyStats.recordMiss();
  if (maximumColdSize < maximumSize) {
    maximumColdSize++;
  }
  delete(node);
  sizeTest--;
  checkState(sizeTest >= 0);
  node.status=Status.HOT;
  add(node);
  sizeHot++;
  checkState((sizeHot + sizeCold) <= maximumSize);
}
