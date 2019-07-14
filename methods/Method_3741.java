/** 
 * This can be used to find a disappearing view by position.
 * @param position The adapter position of the item.
 * @return         A hidden view with a valid ViewHolder that matches the position.
 */
View findHiddenNonRemovedView(int position){
  final int count=mHiddenViews.size();
  for (int i=0; i < count; i++) {
    final View view=mHiddenViews.get(i);
    RecyclerView.ViewHolder holder=mCallback.getChildViewHolder(view);
    if (holder.getLayoutPosition() == position && !holder.isInvalid() && !holder.isRemoved()) {
      return view;
    }
  }
  return null;
}
