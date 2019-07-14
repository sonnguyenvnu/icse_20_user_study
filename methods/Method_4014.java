/** 
 * @param startChild View closest to start of the list. (top or left)
 * @param endChild   View closest to end of the list (bottom or right)
 */
static int computeScrollExtent(RecyclerView.State state,OrientationHelper orientation,View startChild,View endChild,RecyclerView.LayoutManager lm,boolean smoothScrollbarEnabled){
  if (lm.getChildCount() == 0 || state.getItemCount() == 0 || startChild == null || endChild == null) {
    return 0;
  }
  if (!smoothScrollbarEnabled) {
    return Math.abs(lm.getPosition(startChild) - lm.getPosition(endChild)) + 1;
  }
  final int extend=orientation.getDecoratedEnd(endChild) - orientation.getDecoratedStart(startChild);
  return Math.min(orientation.getTotalSpace(),extend);
}
