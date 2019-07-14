private void onMainHit(Node node){
  policyStats.recordHit();
  hitsInSample++;
  sample++;
  boolean wasBottom=(headMain.prevMain == node);
  node.moveToTop(StackType.MAIN);
  if (wasBottom) {
    pruneStack();
  }
}
