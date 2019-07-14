private void mentionListViewUpdateLayout(){
  if (mentionListView.getChildCount() <= 0) {
    mentionListViewScrollOffsetY=0;
    mentionListViewLastViewPosition=-1;
    return;
  }
  View child=mentionListView.getChildAt(mentionListView.getChildCount() - 1);
  RecyclerListView.Holder holder=(RecyclerListView.Holder)mentionListView.findContainingViewHolder(child);
  if (mentionLayoutManager.getReverseLayout()) {
    if (holder != null) {
      mentionListViewLastViewPosition=holder.getAdapterPosition();
      mentionListViewLastViewTop=child.getBottom();
    }
 else {
      mentionListViewLastViewPosition=-1;
    }
    child=mentionListView.getChildAt(0);
    holder=(RecyclerListView.Holder)mentionListView.findContainingViewHolder(child);
    int newOffset=child.getBottom() < mentionListView.getMeasuredHeight() && holder != null && holder.getAdapterPosition() == 0 ? child.getBottom() : mentionListView.getMeasuredHeight();
    if (mentionListViewScrollOffsetY != newOffset) {
      mentionListView.setBottomGlowOffset(mentionListViewScrollOffsetY=newOffset);
      mentionListView.setTopGlowOffset(0);
      mentionListView.invalidate();
      mentionContainer.invalidate();
    }
  }
 else {
    if (holder != null) {
      mentionListViewLastViewPosition=holder.getAdapterPosition();
      mentionListViewLastViewTop=child.getTop();
    }
 else {
      mentionListViewLastViewPosition=-1;
    }
    child=mentionListView.getChildAt(0);
    holder=(RecyclerListView.Holder)mentionListView.findContainingViewHolder(child);
    int newOffset=child.getTop() > 0 && holder != null && holder.getAdapterPosition() == 0 ? child.getTop() : 0;
    if (mentionListViewScrollOffsetY != newOffset) {
      mentionListView.setTopGlowOffset(mentionListViewScrollOffsetY=newOffset);
      mentionListView.setBottomGlowOffset(0);
      mentionListView.invalidate();
      mentionContainer.invalidate();
    }
  }
}
