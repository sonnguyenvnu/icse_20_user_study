@SuppressLint("NewApi") private void updateLayout(){
  if (gridView.getChildCount() <= 0) {
    gridView.setTopGlowOffset(scrollOffsetY=gridView.getPaddingTop());
    if (stickerSetCovereds == null) {
      titleTextView.setTranslationY(scrollOffsetY);
      optionsButton.setTranslationY(scrollOffsetY);
      shadow[0].setTranslationY(scrollOffsetY);
    }
    containerView.invalidate();
    return;
  }
  View child=gridView.getChildAt(0);
  RecyclerListView.Holder holder=(RecyclerListView.Holder)gridView.findContainingViewHolder(child);
  int top=child.getTop();
  int newOffset=0;
  if (top >= 0 && holder != null && holder.getAdapterPosition() == 0) {
    newOffset=top;
    runShadowAnimation(0,false);
  }
 else {
    runShadowAnimation(0,true);
  }
  if (scrollOffsetY != newOffset) {
    gridView.setTopGlowOffset(scrollOffsetY=newOffset);
    if (stickerSetCovereds == null) {
      titleTextView.setTranslationY(scrollOffsetY);
      optionsButton.setTranslationY(scrollOffsetY);
      shadow[0].setTranslationY(scrollOffsetY);
    }
    containerView.invalidate();
  }
}
