/** 
 * @param startChild View closest to start of the list. (top or left)
 * @param endChild   View closest to end of the list (bottom or right)
 */
static int computeScrollRange(RecyclerView.State state,OrientationHelper orientation,View startChild,View endChild,RecyclerView.LayoutManager lm,boolean smoothScrollbarEnabled){
  if (lm.getChildCount() == 0 || state.getItemCount() == 0 || startChild == null || endChild == null) {
    return 0;
  }
  if (!smoothScrollbarEnabled) {
    return state.getItemCount();
  }
  final int laidOutArea=orientation.getDecoratedEnd(endChild) - orientation.getDecoratedStart(startChild);
  final int laidOutRange=Math.abs(lm.getPosition(startChild) - lm.getPosition(endChild)) + 1;
  return (int)((float)laidOutArea / laidOutRange * state.getItemCount());
}
