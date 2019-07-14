private void checkListViewScroll(){
  if (listView.getChildCount() <= 0 || openAnimationInProgress) {
    return;
  }
  View child=listView.getChildAt(0);
  RecyclerListView.Holder holder=(RecyclerListView.Holder)listView.findContainingViewHolder(child);
  int top=child.getTop();
  int newOffset=0;
  if (top >= 0 && holder != null && holder.getAdapterPosition() == 0) {
    newOffset=top;
  }
  if (extraHeight != newOffset) {
    extraHeight=newOffset;
    topView.invalidate();
    if (playProfileAnimation) {
      allowProfileAnimation=extraHeight != 0;
    }
    needLayout();
  }
}
