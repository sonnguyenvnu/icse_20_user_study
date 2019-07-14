/** 
 * Returns the adapter position of the last fully visible view. This position does not include adapter changes that were dispatched after the last layout pass. <p> Note that bounds check is only performed in the current orientation. That means, if LayoutManager is horizontal, it will only check the view's left and right edges.
 * @return The adapter position of the last fully visible view or{@link RecyclerView#NO_POSITION} if there aren't any visible items.
 * @see #findLastVisibleItemPosition()
 * @see #findFirstCompletelyVisibleItemPosition()
 */
public int findLastCompletelyVisibleItemPosition(){
  final View child=findOneVisibleChild(getChildCount() - 1,-1,true,false);
  return child == null ? RecyclerView.NO_POSITION : getPosition(child);
}
