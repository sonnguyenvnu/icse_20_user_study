/** 
 * Utility method for clearing holder's internal RecyclerView, if present
 */
static void clearNestedRecyclerViewIfNotNested(@NonNull ViewHolder holder){
  if (holder.mNestedRecyclerView != null) {
    View item=holder.mNestedRecyclerView.get();
    while (item != null) {
      if (item == holder.itemView) {
        return;
      }
      ViewParent parent=item.getParent();
      if (parent instanceof View) {
        item=(View)parent;
      }
 else {
        item=null;
      }
    }
    holder.mNestedRecyclerView=null;
  }
}
