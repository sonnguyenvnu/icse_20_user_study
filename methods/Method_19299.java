private void maybeUpdateRangeOrRemeasureForMutationEstimateRangeSize(){
  if (!mSplitLayoutForMeasureAndRangeEstimation) {
    throw new RuntimeException("This should only be invoked if ComponentsConfiguration.splitLayoutForMeasureAndRangeEstimation is true");
  }
  if (!mIsMeasured.get()) {
    return;
  }
  if (mRequiresRemeasure.get()) {
    requestRemeasure();
    return;
  }
  if (!hasComputedRange()) {
    final int initialComponentPosition=findInitialComponentPosition(mComponentTreeHolders,mTraverseLayoutBackwards);
    if (initialComponentPosition >= 0) {
      final ComponentTreeHolderRangeInfo holderRangeInfo=new ComponentTreeHolderRangeInfo(initialComponentPosition,mComponentTreeHolders);
      estimateRangeSize(mMeasuredSize.width,mMeasuredSize.height,holderRangeInfo,new EstimateRangeSizeListener(){
        @Override public void onFinish(){
          maybePostUpdateViewportAndComputeRange();
        }
      }
);
    }
  }
}
