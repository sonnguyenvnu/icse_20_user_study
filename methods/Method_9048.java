@SuppressLint("NewApi") private void updateLayout(){
  if (listView.getChildCount() <= 0) {
    listView.setTopGlowOffset(scrollOffsetY=listView.getPaddingTop());
    containerView.invalidate();
    return;
  }
  View child=listView.getChildAt(0);
  RecyclerListView.Holder holder=(RecyclerListView.Holder)listView.findContainingViewHolder(child);
  int top=child.getTop() - AndroidUtilities.dp(8);
  int newOffset=top > 0 && holder != null && holder.getAdapterPosition() == 0 ? top : 0;
  if (scrollOffsetY != newOffset) {
    listView.setTopGlowOffset(scrollOffsetY=newOffset);
    containerView.invalidate();
  }
}
