private void recursivelySetVisibleHint(boolean isVisible){
  final List<LithoView> childLithoViews=mMountState.getChildLithoViewsFromCurrentlyMountedItems();
  for (int i=childLithoViews.size() - 1; i >= 0; i--) {
    final LithoView lithoView=childLithoViews.get(i);
    lithoView.setVisibilityHint(isVisible);
  }
}
