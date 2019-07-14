@GuardedBy("this") private void fillListViewportSetRangeSize(int maxWidth,int maxHeight,@Nullable Size outSize){
  if (!mSplitLayoutForMeasureAndRangeEstimation) {
    throw new RuntimeException("This should only be invoked if ComponentsConfiguration.splitLayoutForMeasureAndRangeEstimation is true");
  }
  ComponentsSystrace.beginSection("fillListViewport");
  final int firstVisiblePosition=mWrapContent ? 0 : mLayoutInfo.findFirstVisibleItemPosition();
  final int startIndex=firstVisiblePosition != RecyclerView.NO_POSITION ? firstVisiblePosition : 0;
  final int itemCount=computeLayoutsToFillListViewport(mComponentTreeHolders,startIndex,maxWidth,maxHeight,outSize);
  if (mEstimatedViewportCount == UNSET) {
    final ComponentTreeHolderRangeInfo holderForRangeInfo=getHolderForRangeInfo();
    if (holderForRangeInfo != null) {
      setRangeSize(outSize.width / itemCount,outSize.height / itemCount,outSize.width,outSize.height);
    }
  }
  ComponentsSystrace.endSection();
}
