private void onFilterHit(Node node){
  policyStats.recordHit();
  node.moveToTop(StackType.FILTER);
  node.moveToTop(StackType.MAIN);
}
