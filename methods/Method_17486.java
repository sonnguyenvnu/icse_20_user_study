private void onNonResidentHit(Node node){
  policyStats.recordEviction();
  policyStats.recordMiss();
  pruneStack();
  Node victim=headMain.prevMain;
  victim.removeFrom(StackType.MAIN);
  data.remove(victim.key);
  pruneStack();
  node.moveToTop(StackType.MAIN);
  node.status=Status.MAIN;
  data.put(node.key,node);
}
