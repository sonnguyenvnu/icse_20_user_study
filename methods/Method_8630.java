private void checkStickersSearchFieldScroll(boolean isLayout){
  if (delegate != null && delegate.isSearchOpened()) {
    RecyclerView.ViewHolder holder=stickersGridView.findViewHolderForAdapterPosition(0);
    if (holder == null) {
      stickersSearchField.showShadow(true,!isLayout);
    }
 else {
      stickersSearchField.showShadow(holder.itemView.getTop() < stickersGridView.getPaddingTop(),!isLayout);
    }
    return;
  }
  if (stickersSearchField == null || stickersGridView == null) {
    return;
  }
  RecyclerView.ViewHolder holder=stickersGridView.findViewHolderForAdapterPosition(0);
  if (holder != null) {
    stickersSearchField.setTranslationY(holder.itemView.getTop());
  }
 else {
    stickersSearchField.setTranslationY(-searchFieldHeight);
  }
  stickersSearchField.showShadow(false,!isLayout);
}
