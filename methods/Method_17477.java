private void onMiss(Node node){
  policyStats.recordMiss();
  if (residentSize < maximumMainResidentSize) {
    onMainWarmupMiss(node);
    residentSize++;
    residentMain++;
  }
 else   if (residentSize < maximumSize) {
    onFilterWarmupMiss(node);
    residentSize++;
    residentFilter++;
  }
 else   if (residentFilter < maximumFilterSize) {
    adaptMainToFilter(node);
  }
 else {
    onFullMiss(node);
  }
}
