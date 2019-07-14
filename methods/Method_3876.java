/** 
 * Calculates the horizontal scroll amount necessary to make the given view fully visible inside the RecyclerView.
 * @param view           The view which we want to make fully visible
 * @param snapPreference The edge which the view should snap to when entering the visiblearea. One of  {@link #SNAP_TO_START},  {@link #SNAP_TO_END} or{@link #SNAP_TO_END}
 * @return The vertical scroll amount necessary to make the view visible with the givensnap preference.
 */
public int calculateDxToMakeVisible(View view,int snapPreference){
  final RecyclerView.LayoutManager layoutManager=getLayoutManager();
  if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
    return 0;
  }
  final RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)view.getLayoutParams();
  final int left=layoutManager.getDecoratedLeft(view) - params.leftMargin;
  final int right=layoutManager.getDecoratedRight(view) + params.rightMargin;
  final int start=layoutManager.getPaddingLeft();
  final int end=layoutManager.getWidth() - layoutManager.getPaddingRight();
  return calculateDtToFit(left,right,start,end,snapPreference);
}
