/** 
 * Calculates the vertical scroll amount necessary to make the given view fully visible inside the RecyclerView.
 * @param view           The view which we want to make fully visible
 * @param snapPreference The edge which the view should snap to when entering the visiblearea. One of  {@link #SNAP_TO_START},  {@link #SNAP_TO_END} or{@link #SNAP_TO_ANY}.
 * @return The vertical scroll amount necessary to make the view visible with the givensnap preference.
 */
public int calculateDyToMakeVisible(View view,int snapPreference){
  final RecyclerView.LayoutManager layoutManager=getLayoutManager();
  if (layoutManager == null || !layoutManager.canScrollVertically()) {
    return 0;
  }
  final RecyclerView.LayoutParams params=(RecyclerView.LayoutParams)view.getLayoutParams();
  final int top=layoutManager.getDecoratedTop(view) - params.topMargin;
  final int bottom=layoutManager.getDecoratedBottom(view) + params.bottomMargin;
  final int start=layoutManager.getPaddingTop();
  final int end=layoutManager.getHeight() - layoutManager.getPaddingBottom();
  return calculateDtToFit(top,bottom,start,end,snapPreference);
}
