private int computeScrollRangeWithSpanInfo(RecyclerView.State state){
  if (getChildCount() == 0 || state.getItemCount() == 0) {
    return 0;
  }
  ensureLayoutState();
  View startChild=findFirstVisibleChildClosestToStart(!isSmoothScrollbarEnabled(),true);
  View endChild=findFirstVisibleChildClosestToEnd(!isSmoothScrollbarEnabled(),true);
  if (startChild == null || endChild == null) {
    return 0;
  }
  if (!isSmoothScrollbarEnabled()) {
    return mSpanSizeLookup.getCachedSpanGroupIndex(state.getItemCount() - 1,mSpanCount) + 1;
  }
  final int laidOutArea=mOrientationHelper.getDecoratedEnd(endChild) - mOrientationHelper.getDecoratedStart(startChild);
  final int firstVisibleSpan=mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(startChild),mSpanCount);
  final int lastVisibleSpan=mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(endChild),mSpanCount);
  final int totalSpans=mSpanSizeLookup.getCachedSpanGroupIndex(state.getItemCount() - 1,mSpanCount) + 1;
  final int laidOutSpans=lastVisibleSpan - firstVisibleSpan + 1;
  return (int)(((float)laidOutArea / laidOutSpans) * totalSpans);
}
