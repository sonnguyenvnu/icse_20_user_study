private void onMiss(Node node){
  policyStats.recordMiss();
  if (residentSize < maximumMainResidentSize) {
    onMainWarmupMiss(node);
    residentSize++;
  }
 else   if (residentSize < maximumSize) {
    onFilterWarmupMiss(node);
    residentSize++;
  }
 else {
    onFullMiss(node);
  }
}
