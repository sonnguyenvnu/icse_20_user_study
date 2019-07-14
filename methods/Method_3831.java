/** 
 * @return The final offset amount for children
 */
private int fixLayoutEndGap(int endOffset,RecyclerView.Recycler recycler,RecyclerView.State state,boolean canOffsetChildren){
  int gap=mOrientationHelper.getEndAfterPadding() - endOffset;
  int fixOffset=0;
  if (gap > 0) {
    fixOffset=-scrollBy(-gap,recycler,state);
  }
 else {
    return 0;
  }
  endOffset+=fixOffset;
  if (canOffsetChildren) {
    gap=mOrientationHelper.getEndAfterPadding() - endOffset;
    if (gap > 0) {
      mOrientationHelper.offsetChildren(gap);
      return gap + fixOffset;
    }
  }
  return fixOffset;
}
