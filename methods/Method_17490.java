private void onNonResidentHir(Node node){
  policyStats.recordMiss();
  if (sizeHot < maximumHotSize) {
    onLirWarmupMiss(node);
  }
 else   if (residentSize < maximumSize) {
    onHirWarmupMiss(node);
  }
 else {
    onFullMiss(node);
  }
  residentSize++;
}
