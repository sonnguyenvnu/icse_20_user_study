private void fixStartGap(RecyclerView.Recycler recycler,RecyclerView.State state,boolean canOffsetChildren){
  final int minStartLine=getMinStart(Integer.MAX_VALUE);
  if (minStartLine == Integer.MAX_VALUE) {
    return;
  }
  int gap=minStartLine - mPrimaryOrientation.getStartAfterPadding();
  int fixOffset;
  if (gap > 0) {
    fixOffset=scrollBy(gap,recycler,state);
  }
 else {
    return;
  }
  gap-=fixOffset;
  if (canOffsetChildren && gap > 0) {
    mPrimaryOrientation.offsetChildren(-gap);
  }
}
