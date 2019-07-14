private void onMainHit(Node node){
  policyStats.recordHit();
  boolean wasBottom=(headMain.prevMain == node);
  node.moveToTop(StackType.MAIN);
  if (wasBottom) {
    pruneStack();
  }
}
