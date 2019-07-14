private void fixEndGap(RecyclerView.Recycler recycler,RecyclerView.State state,boolean canOffsetChildren){
  final int maxEndLine=getMaxEnd(Integer.MIN_VALUE);
  if (maxEndLine == Integer.MIN_VALUE) {
    return;
  }
  int gap=mPrimaryOrientation.getEndAfterPadding() - maxEndLine;
  int fixOffset;
  if (gap > 0) {
    fixOffset=-scrollBy(-gap,recycler,state);
  }
 else {
    return;
  }
  gap-=fixOffset;
  if (canOffsetChildren && gap > 0) {
    mPrimaryOrientation.offsetChildren(gap);
  }
}
