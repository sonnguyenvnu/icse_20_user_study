private void repositionToWrapContentIfNecessary(){
  if (mSecondaryOrientation.getMode() == View.MeasureSpec.EXACTLY) {
    return;
  }
  float maxSize=0;
  final int childCount=getChildCount();
  for (int i=0; i < childCount; i++) {
    View child=getChildAt(i);
    float size=mSecondaryOrientation.getDecoratedMeasurement(child);
    if (size < maxSize) {
      continue;
    }
    LayoutParams layoutParams=(LayoutParams)child.getLayoutParams();
    if (layoutParams.isFullSpan()) {
      size=1f * size / mSpanCount;
    }
    maxSize=Math.max(maxSize,size);
  }
  int before=mSizePerSpan;
  int desired=Math.round(maxSize * mSpanCount);
  if (mSecondaryOrientation.getMode() == View.MeasureSpec.AT_MOST) {
    desired=Math.min(desired,mSecondaryOrientation.getTotalSpace());
  }
  updateMeasureSpecs(desired);
  if (mSizePerSpan == before) {
    return;
  }
  for (int i=0; i < childCount; i++) {
    View child=getChildAt(i);
    final LayoutParams lp=(LayoutParams)child.getLayoutParams();
    if (lp.mFullSpan) {
      continue;
    }
    if (isLayoutRTL() && mOrientation == VERTICAL) {
      int newOffset=-(mSpanCount - 1 - lp.mSpan.mIndex) * mSizePerSpan;
      int prevOffset=-(mSpanCount - 1 - lp.mSpan.mIndex) * before;
      child.offsetLeftAndRight(newOffset - prevOffset);
    }
 else {
      int newOffset=lp.mSpan.mIndex * mSizePerSpan;
      int prevOffset=lp.mSpan.mIndex * before;
      if (mOrientation == VERTICAL) {
        child.offsetLeftAndRight(newOffset - prevOffset);
      }
 else {
        child.offsetTopAndBottom(newOffset - prevOffset);
      }
    }
  }
}
