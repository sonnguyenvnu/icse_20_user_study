@GuardedBy("this") private boolean isCompatibleSize(int widthSpec,int heightSpec){
  final int scrollDirection=mLayoutInfo.getScrollDirection();
  if (mLastWidthSpec != LayoutManagerOverrideParams.UNINITIALIZED) {
switch (scrollDirection) {
case HORIZONTAL:
      return isMeasureSpecCompatible(mLastHeightSpec,heightSpec,mMeasuredSize.height);
case VERTICAL:
    return isMeasureSpecCompatible(mLastWidthSpec,widthSpec,mMeasuredSize.width);
}
}
return false;
}
