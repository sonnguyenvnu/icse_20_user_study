/** 
 * Traverses the ancestors of the given view and returns the item view that contains it and also a direct child of the RecyclerView. This returned view can be used to get the ViewHolder by calling  {@link #getChildViewHolder(View)}.
 * @param view The view that is a descendant of the RecyclerView.
 * @return The direct child of the RecyclerView which contains the given view or null if theprovided view is not a descendant of this RecyclerView.
 * @see #getChildViewHolder(View)
 * @see #findContainingViewHolder(View)
 */
@Nullable public View findContainingItemView(@NonNull View view){
  ViewParent parent=view.getParent();
  while (parent != null && parent != this && parent instanceof View) {
    view=(View)parent;
    parent=view.getParent();
  }
  return parent == this ? view : null;
}
