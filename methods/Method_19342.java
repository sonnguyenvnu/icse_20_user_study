@VisibleForTesting void onNewWorkingRange(int firstVisibleIndex,int lastVisibleIndex,int firstFullyVisibleIndex,int lastFullyVisibleIndex){
  if (mEstimatedViewportCount == UNSET || firstVisibleIndex == RecyclerView.NO_POSITION || lastVisibleIndex == RecyclerView.NO_POSITION) {
    return;
  }
  final int rangeSize=Math.max(mEstimatedViewportCount,lastVisibleIndex - firstVisibleIndex);
  final int layoutRangeSize=(int)(rangeSize * mRangeRatio);
  final int rangeStart=Math.max(0,firstVisibleIndex - layoutRangeSize);
  final int rangeEnd=Math.min(firstVisibleIndex + rangeSize + layoutRangeSize,mComponentTreeHolders.size() - 1);
  for (int position=rangeStart; position <= rangeEnd; position++) {
    final ComponentTreeHolder holder=mComponentTreeHolders.get(position);
    holder.checkWorkingRangeAndDispatch(position,firstVisibleIndex,lastVisibleIndex,firstFullyVisibleIndex,lastFullyVisibleIndex);
  }
}
