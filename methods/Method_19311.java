@GuardedBy("this") private void fillListViewportInitRange(int maxWidth,int maxHeight,@Nullable Size outSize){
  if (mSplitLayoutForMeasureAndRangeEstimation) {
    throw new RuntimeException("This should only be invoked if ComponentsConfiguration.splitLayoutForMeasureAndRangeEstimation is false");
  }
  ComponentsSystrace.beginSection("fillListViewport");
  final int firstVisiblePosition=mWrapContent ? 0 : mLayoutInfo.findFirstVisibleItemPosition();
  final int startIndex=firstVisiblePosition != RecyclerView.NO_POSITION ? firstVisiblePosition : 0;
  computeLayoutsToFillListViewport(mComponentTreeHolders,startIndex,maxWidth,maxHeight,outSize);
  if (!hasComputedRange()) {
    final ComponentTreeHolderRangeInfo holderForRangeInfo=getHolderForRangeInfo();
    if (holderForRangeInfo != null) {
      initRange(maxWidth,maxHeight,holderForRangeInfo,mLayoutInfo.getScrollDirection());
    }
  }
  ComponentsSystrace.endSection();
}
