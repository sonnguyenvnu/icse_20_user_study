private void checkEmojiTabY(View list,int dy){
  if (list == null) {
    emojiTabs.setTranslationY(emojiMinusDy=0);
    emojiTabsShadow.setTranslationY(emojiMinusDy);
    return;
  }
  if (list.getVisibility() != VISIBLE) {
    return;
  }
  if (delegate != null && delegate.isSearchOpened()) {
    return;
  }
  if (dy > 0 && emojiGridView != null && emojiGridView.getVisibility() == VISIBLE) {
    RecyclerView.ViewHolder holder=emojiGridView.findViewHolderForAdapterPosition(0);
    if (holder != null && holder.itemView.getTop() + (needEmojiSearch ? searchFieldHeight : 0) >= emojiGridView.getPaddingTop()) {
      return;
    }
  }
  emojiMinusDy-=dy;
  if (emojiMinusDy > 0) {
    emojiMinusDy=0;
  }
 else   if (emojiMinusDy < -AndroidUtilities.dp(48 * 6)) {
    emojiMinusDy=-AndroidUtilities.dp(48 * 6);
  }
  emojiTabs.setTranslationY(Math.max(-AndroidUtilities.dp(38),emojiMinusDy));
  emojiTabsShadow.setTranslationY(emojiTabs.getTranslationY());
}
