/** 
 * Adds a view to the animatingViews list. mAnimatingViews holds the child views that are currently being kept around purely for the purpose of being animated out of view. They are drawn as a regular part of the child list of the RecyclerView, but they are invisible to the LayoutManager as they are managed separately from the regular child views.
 * @param viewHolder The ViewHolder to be removed
 */
private void addAnimatingView(ViewHolder viewHolder){
  final View view=viewHolder.itemView;
  final boolean alreadyParented=view.getParent() == this;
  mRecycler.unscrapView(getChildViewHolder(view));
  if (viewHolder.isTmpDetached()) {
    mChildHelper.attachViewToParent(view,-1,view.getLayoutParams(),true);
  }
 else   if (!alreadyParented) {
    mChildHelper.addView(view,true);
  }
 else {
    mChildHelper.hide(view);
  }
}
