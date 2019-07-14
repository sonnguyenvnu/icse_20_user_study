private int getCurrentTop(){
  if (listView.getChildCount() != 0) {
    View child=listView.getChildAt(0);
    RecyclerListView.Holder holder=(RecyclerListView.Holder)listView.findContainingViewHolder(child);
    if (holder != null) {
      return listView.getPaddingTop() - (holder.getAdapterPosition() == 0 && child.getTop() >= 0 ? child.getTop() : 0);
    }
  }
  return -1000;
}
