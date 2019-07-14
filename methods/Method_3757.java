private void horizontalScrollTo(float x){
  final int[] scrollbarRange=getHorizontalRange();
  x=Math.max(scrollbarRange[0],Math.min(scrollbarRange[1],x));
  if (Math.abs(mHorizontalThumbCenterX - x) < 2) {
    return;
  }
  int scrollingBy=scrollTo(mHorizontalDragX,x,scrollbarRange,mRecyclerView.computeHorizontalScrollRange(),mRecyclerView.computeHorizontalScrollOffset(),mRecyclerViewWidth);
  if (scrollingBy != 0) {
    mRecyclerView.scrollBy(scrollingBy,0);
  }
  mHorizontalDragX=x;
}
