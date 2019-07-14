private void maybeUpdateRangeOrRemeasureForMutation(){
  if (mSplitLayoutForMeasureAndRangeEstimation) {
    maybeUpdateRangeOrRemeasureForMutationEstimateRangeSize();
  }
 else {
    maybeUpdateRangeOrRemeasureForMutationInitRange();
  }
}
