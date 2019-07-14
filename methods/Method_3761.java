private void prefetchInnerRecyclerViewWithDeadline(@Nullable RecyclerView innerView,long deadlineNs){
  if (innerView == null) {
    return;
  }
  if (innerView.mDataSetHasChangedAfterLayout && innerView.mChildHelper.getUnfilteredChildCount() != 0) {
    innerView.removeAndRecycleViews();
  }
  final LayoutPrefetchRegistryImpl innerPrefetchRegistry=innerView.mPrefetchRegistry;
  innerPrefetchRegistry.collectPrefetchPositionsFromView(innerView,true);
  if (innerPrefetchRegistry.mCount != 0) {
    try {
      TraceCompat.beginSection(RecyclerView.TRACE_NESTED_PREFETCH_TAG);
      innerView.mState.prepareForNestedPrefetch(innerView.mAdapter);
      for (int i=0; i < innerPrefetchRegistry.mCount * 2; i+=2) {
        final int innerPosition=innerPrefetchRegistry.mPrefetchArray[i];
        prefetchPositionWithDeadline(innerView,innerPosition,deadlineNs);
      }
    }
  finally {
      TraceCompat.endSection();
    }
  }
}
