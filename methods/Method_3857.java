/** 
 * Returns the adapter position of the last visible view. This position does not include adapter changes that were dispatched after the last layout pass. <p> Note that, this value is not affected by layout orientation or item order traversal. ( {@link #setReverseLayout(boolean)}). Views are sorted by their positions in the adapter, not in the layout. <p> If RecyclerView has item decorators, they will be considered in calculations as well. <p> LayoutManager may pre-cache some views that are not necessarily visible. Those views are ignored in this method.
 * @return The adapter position of the last visible view or {@link RecyclerView#NO_POSITION} ifthere aren't any visible items.
 * @see #findLastCompletelyVisibleItemPosition()
 * @see #findFirstVisibleItemPosition()
 */
public int findLastVisibleItemPosition(){
  final View child=findOneVisibleChild(getChildCount() - 1,-1,false,true);
  return child == null ? RecyclerView.NO_POSITION : getPosition(child);
}
