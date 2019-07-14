/** 
 * Removes a view from the animatingViews list.
 * @param view The view to be removed
 * @see #addAnimatingView(RecyclerView.ViewHolder)
 * @return true if an animating view is removed
 */
boolean removeAnimatingView(View view){
  startInterceptRequestLayout();
  final boolean removed=mChildHelper.removeViewIfHidden(view);
  if (removed) {
    final ViewHolder viewHolder=getChildViewHolderInt(view);
    mRecycler.unscrapView(viewHolder);
    mRecycler.recycleViewHolderInternal(viewHolder);
    if (DEBUG) {
      Log.d(TAG,"after removing animated view: " + view + ", " + this);
    }
  }
  stopInterceptRequestLayout(!removed);
  return removed;
}
