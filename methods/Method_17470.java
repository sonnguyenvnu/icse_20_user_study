private void onFilterHit(Node node){
  policyStats.recordHit();
  hitsInSample++;
  sample++;
  node.moveToTop(StackType.FILTER);
  node.moveToTop(StackType.MAIN);
}
