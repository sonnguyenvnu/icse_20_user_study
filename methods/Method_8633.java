private void checkStickersTabY(View list,int dy){
  if (list == null) {
    stickersTab.setTranslationY(stickersMinusDy=0);
    return;
  }
  if (list.getVisibility() != VISIBLE) {
    return;
  }
  if (delegate != null && delegate.isSearchOpened()) {
    return;
  }
  if (dy > 0 && stickersGridView != null && stickersGridView.getVisibility() == VISIBLE) {
    RecyclerView.ViewHolder holder=stickersGridView.findViewHolderForAdapterPosition(0);
    if (holder != null && holder.itemView.getTop() + searchFieldHeight >= stickersGridView.getPaddingTop()) {
      return;
    }
  }
  stickersMinusDy-=dy;
  if (stickersMinusDy > 0) {
    stickersMinusDy=0;
  }
 else   if (stickersMinusDy < -AndroidUtilities.dp(48 * 6)) {
    stickersMinusDy=-AndroidUtilities.dp(48 * 6);
  }
  stickersTab.setTranslationY(Math.max(-AndroidUtilities.dp(48),stickersMinusDy));
}
