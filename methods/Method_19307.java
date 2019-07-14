/** 
 * Calculates a sync layout for the provided item because we need its size to be able to measure.
 */
private void maybeCalculateSyncLayoutForSize(int widthSpec,int heightSpec,int scrollDirection,boolean shouldMeasureItemForSize,ComponentTreeHolderRangeInfo holderForRangeInfo){
  if (holderForRangeInfo == null) {
    return;
  }
  if (mSplitLayoutForMeasureAndRangeEstimation) {
    if (shouldMeasureItemForSize && mSizeForMeasure == null) {
      layoutItemForSize(holderForRangeInfo);
    }
  }
 else   if (!hasComputedRange()) {
    initRange(SizeSpec.getSize(widthSpec),SizeSpec.getSize(heightSpec),holderForRangeInfo,scrollDirection);
  }
}
