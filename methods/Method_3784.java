@Override void collectPrefetchPositionsForLayoutState(RecyclerView.State state,LayoutState layoutState,LayoutPrefetchRegistry layoutPrefetchRegistry){
  int remainingSpan=mSpanCount;
  int count=0;
  while (count < mSpanCount && layoutState.hasMore(state) && remainingSpan > 0) {
    final int pos=layoutState.mCurrentPosition;
    layoutPrefetchRegistry.addPosition(pos,Math.max(0,layoutState.mScrollingOffset));
    final int spanSize=mSpanSizeLookup.getSpanSize(pos);
    remainingSpan-=spanSize;
    layoutState.mCurrentPosition+=layoutState.mItemDirection;
    count++;
  }
}
