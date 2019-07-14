@Override public void record(long key){
  policyStats.recordOperation();
  adapt(key);
  Node node=data.get(key);
  if (node == null) {
    node=new Node(key);
    data.put(key,node);
    onMiss(node);
  }
 else   if (node.status == Status.FILTER) {
    onFilterHit(node);
  }
 else   if (node.status == Status.MAIN) {
    onMainHit(node);
  }
 else   if (node.status == Status.NON_RESIDENT) {
    if (residentMain < maximumMainResidentSize) {
      checkState(residentFilter > maximumFilterSize);
      adaptFilterToMain(node);
    }
 else {
      onNonResidentHit(node);
    }
  }
 else {
    throw new IllegalStateException();
  }
}
