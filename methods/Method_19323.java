@VisibleForTesting @GuardedBy("this") void initRange(int width,int height,ComponentTreeHolderRangeInfo holderRangeInfo,int scrollDirection){
  if (asyncInitRangeEnabled()) {
    final ComponentAsyncInitRangeIterator asyncInitRangeIterator=new ComponentAsyncInitRangeIterator(holderRangeInfo.mHolders,holderRangeInfo.mPosition,mComponentTreeHolders.size() - 1,mTraverseLayoutBackwards);
    maybeScheduleAsyncLayoutsDuringInitRange(asyncInitRangeIterator);
  }
  final ComponentTreeHolder holder=holderRangeInfo.mHolders.get(holderRangeInfo.mPosition);
  final int childWidthSpec=getActualChildrenWidthSpec(holder);
  final int childHeightSpec=getActualChildrenHeightSpec(holder);
  ComponentsSystrace.beginSection("initRange");
  try {
    final Size size=new Size();
    holder.computeLayoutSync(mComponentContext,childWidthSpec,childHeightSpec,size);
    final int rangeSize=Math.max(mLayoutInfo.approximateRangeSize(size.width,size.height,width,height),1);
    mSizeForMeasure=size;
    mEstimatedViewportCount=rangeSize;
  }
  finally {
    ComponentsSystrace.endSection();
  }
}
