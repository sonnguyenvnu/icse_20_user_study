private void adaptMainToFilter(Node node){
  policyStats.recordEviction();
  pruneStack();
  Node victim=headMain.prevMain;
  victim.removeFrom(StackType.MAIN);
  data.remove(victim.key);
  pruneStack();
  residentMain--;
  node.moveToTop(StackType.FILTER);
  node.moveToTop(StackType.MAIN);
  node.status=Status.FILTER;
  residentFilter++;
}
