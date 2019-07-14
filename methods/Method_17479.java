private void adaptFilterToMain(Node node){
  policyStats.recordEviction();
  policyStats.recordMiss();
  Node victim=headFilter.prevFilter;
  victim.removeFrom(StackType.FILTER);
  if (victim.isInMain) {
    victim.status=Status.NON_RESIDENT;
  }
 else {
    data.remove(victim.key);
  }
  residentFilter--;
  node.moveToTop(StackType.MAIN);
  node.status=Status.MAIN;
  data.put(node.key,node);
  residentMain++;
}
