private void maybeComputeRangeAfterMeasure(int widthSpec,int heightSpec,ComponentTreeHolderRangeInfo holderForRangeInfo){
  if (mSplitLayoutForMeasureAndRangeEstimation) {
    estimateRangeSize(SizeSpec.getSize(widthSpec),SizeSpec.getSize(heightSpec),holderForRangeInfo,new EstimateRangeSizeListener(){
      @Override public void onFinish(){
        computeRange(mCurrentFirstVisiblePosition,mCurrentLastVisiblePosition);
      }
    }
);
  }
 else {
    if (mEstimatedViewportCount != RecyclerView.NO_POSITION) {
      computeRange(mCurrentFirstVisiblePosition,mCurrentLastVisiblePosition);
    }
  }
}
