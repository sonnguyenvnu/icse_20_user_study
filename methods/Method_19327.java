@GuardedBy("this") private void resetMeasuredSize(int width){
  if (mSizeForMeasure == null) {
    return;
  }
  int maxHeight=0;
  for (int i=0, size=mComponentTreeHolders.size(); i < size; i++) {
    final ComponentTreeHolder holder=mComponentTreeHolders.get(i);
    final int measuredItemHeight=holder.getMeasuredHeight();
    if (measuredItemHeight > maxHeight) {
      maxHeight=measuredItemHeight;
    }
  }
  if (maxHeight == mSizeForMeasure.height) {
    return;
  }
  final int rangeSize=Math.max(mLayoutInfo.approximateRangeSize(SizeSpec.getSize(mLastWidthSpec),SizeSpec.getSize(mLastHeightSpec),width,maxHeight),1);
  mSizeForMeasure.height=maxHeight;
  mEstimatedViewportCount=rangeSize;
}
