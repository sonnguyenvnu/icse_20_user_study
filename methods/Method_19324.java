/** 
 * Based on the existing measured layouts it estimates a size for the async range. If no layouts have been measured with valid size specs, schedules an async layout and estimates the range when it returns with an item size. When a range size is determined, either immediately or async, it optionally invokes the provided callback.
 */
private void estimateRangeSize(final int width,final int height,@Nullable ComponentTreeHolderRangeInfo holderRangeInfo,@Nullable final EstimateRangeSizeListener listener){
  if (mEstimatedViewportCount != UNSET) {
    if (listener != null) {
      listener.onFinish();
    }
    return;
  }
  if (mSizeForMeasure != null) {
    setRangeSize(mSizeForMeasure.width,mSizeForMeasure.height,width,height);
    if (listener != null) {
      listener.onFinish();
    }
    return;
  }
  if (holderRangeInfo == null) {
    return;
  }
  final ComponentTreeHolder holder=holderRangeInfo.mHolders.get(holderRangeInfo.mPosition);
  final int childWidthSpec=getActualChildrenWidthSpec(holder);
  final int childHeightSpec=getActualChildrenHeightSpec(holder);
  ComponentsSystrace.beginSectionAsync("estimateRangeSize");
  holder.computeLayoutAsync(mComponentContext,childWidthSpec,childHeightSpec,new MeasureListener(){
    @Override public void onSetRootAndSizeSpec(    int itemWidth,    int itemHeight){
      ComponentsSystrace.endSectionAsync("estimateRangeSize");
      setRangeSize(itemWidth,itemHeight,width,height);
      if (listener != null) {
        listener.onFinish();
      }
      holder.updateMeasureListener(null);
    }
  }
);
}
