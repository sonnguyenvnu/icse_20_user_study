@SuppressLint("NewApi") private void updateLayout(){
  if (listView.getChildCount() <= 0) {
    listView.setTopGlowOffset(scrollOffsetY=listView.getPaddingTop());
    listView.invalidate();
    return;
  }
  View child=listView.getChildAt(0);
  RecyclerListView.Holder holder=(RecyclerListView.Holder)listView.findContainingViewHolder(child);
  int top=child.getTop();
  int newOffset=0;
  if (top >= 0 && holder != null && holder.getAdapterPosition() == 0) {
    newOffset=top;
  }
  if (scrollOffsetY != newOffset) {
    listView.setTopGlowOffset(scrollOffsetY=newOffset);
    listView.invalidate();
  }
}
