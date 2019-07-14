private void initStickyHeader(int stickyHeaderPosition){
  final ComponentTree componentTree=mHasStickyHeader.getComponentForStickyHeaderAt(stickyHeaderPosition);
  detachLithoViewIfNeeded(componentTree.getLithoView());
  mSectionsRecyclerView.setStickyComponent(componentTree);
}
