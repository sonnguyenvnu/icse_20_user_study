@Override protected int computeVerticalScrollRange(){
  final int count=getChildCount();
  final int contentHeight=mShowHeight;
  if (count == 0) {
    return contentHeight;
  }
  int scrollRange=mTotalLength;
  final int scrollY=mRxScrollDelegate.getViewScrollY();
  final int overscrollBottom=Math.max(0,scrollRange - contentHeight);
  if (scrollY < 0) {
    scrollRange-=scrollY;
  }
 else   if (scrollY > overscrollBottom) {
    scrollRange+=scrollY - overscrollBottom;
  }
  return scrollRange;
}
