/** 
 * Return the ViewHolder for the item in the given position of the data set. Unlike {@link #findViewHolderForLayoutPosition(int)} this method takes into account any pendingadapter changes that may not be reflected to the layout yet. On the other hand, if {@link Adapter#notifyDataSetChanged()} has been called but the new layout has not beencalculated yet, this method will return <code>null</code> since the new positions of views are unknown until the layout is calculated. <p> This method checks only the children of RecyclerView. If the item at the given <code>position</code> is not laid out, it <em>will not</em> create a new one. <p> When the ItemAnimator is running a change animation, there might be 2 ViewHolders representing the same Item. In this case, the updated ViewHolder will be returned.
 * @param position The position of the item in the data set of the adapter
 * @return The ViewHolder at <code>position</code> or null if there is no such item
 */
@Nullable public ViewHolder findViewHolderForAdapterPosition(int position){
  if (mDataSetHasChangedAfterLayout) {
    return null;
  }
  final int childCount=mChildHelper.getUnfilteredChildCount();
  ViewHolder hidden=null;
  for (int i=0; i < childCount; i++) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
    if (holder != null && !holder.isRemoved() && getAdapterPositionFor(holder) == position) {
      if (mChildHelper.isHidden(holder.itemView)) {
        hidden=holder;
      }
 else {
        return holder;
      }
    }
  }
  return hidden;
}
