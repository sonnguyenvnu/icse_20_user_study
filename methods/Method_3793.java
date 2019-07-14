private int computeScrollOffsetWithSpanInfo(RecyclerView.State state){
  if (getChildCount() == 0 || state.getItemCount() == 0) {
    return 0;
  }
  ensureLayoutState();
  boolean smoothScrollEnabled=isSmoothScrollbarEnabled();
  View startChild=findFirstVisibleChildClosestToStart(!smoothScrollEnabled,true);
  View endChild=findFirstVisibleChildClosestToEnd(!smoothScrollEnabled,true);
  if (startChild == null || endChild == null) {
    return 0;
  }
  int startChildSpan=mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(startChild),mSpanCount);
  int endChildSpan=mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(endChild),mSpanCount);
  final int minSpan=Math.min(startChildSpan,endChildSpan);
  final int maxSpan=Math.max(startChildSpan,endChildSpan);
  final int totalSpans=mSpanSizeLookup.getCachedSpanGroupIndex(state.getItemCount() - 1,mSpanCount) + 1;
  final int spansBefore=mShouldReverseLayout ? Math.max(0,totalSpans - maxSpan - 1) : Math.max(0,minSpan);
  if (!smoothScrollEnabled) {
    return spansBefore;
  }
  final int laidOutArea=Math.abs(mOrientationHelper.getDecoratedEnd(endChild) - mOrientationHelper.getDecoratedStart(startChild));
  final int firstVisibleSpan=mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(startChild),mSpanCount);
  final int lastVisibleSpan=mSpanSizeLookup.getCachedSpanGroupIndex(getPosition(endChild),mSpanCount);
  final int laidOutSpans=lastVisibleSpan - firstVisibleSpan + 1;
  final float avgSizePerSpan=(float)laidOutArea / laidOutSpans;
  return Math.round(spansBefore * avgSizePerSpan + (mOrientationHelper.getStartAfterPadding() - mOrientationHelper.getDecoratedStart(startChild)));
}
