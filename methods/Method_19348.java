@VisibleForTesting @Nullable RangeCalculationResult getRangeCalculationResult(){
  if (mSizeForMeasure == null && mEstimatedViewportCount == UNSET) {
    return null;
  }
  final RangeCalculationResult range=new RangeCalculationResult();
  range.measuredSize=getSizeForMeasuring();
  range.estimatedViewportCount=mEstimatedViewportCount;
  return range;
}
