private void onFullMiss(Node node){
  policyStats.recordEviction();
  Node victim=headFilter.prevFilter;
  victim.removeFrom(StackType.FILTER);
  if (victim.isInMain) {
    victim.status=Status.NON_RESIDENT;
  }
 else {
    data.remove(victim.key);
  }
  node.moveToTop(StackType.FILTER);
  node.moveToTop(StackType.MAIN);
  node.status=Status.FILTER;
}
