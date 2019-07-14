@SuppressLint("NewApi") private void updateLayout(){
  if (gridView.getChildCount() <= 0) {
    return;
  }
  View child=gridView.getChildAt(0);
  RecyclerListView.Holder holder=(RecyclerListView.Holder)gridView.findContainingViewHolder(child);
  int top=child.getTop() - AndroidUtilities.dp(8);
  int newOffset=top > 0 && holder != null && holder.getAdapterPosition() == 0 ? top : 0;
  if (top >= 0 && holder != null && holder.getAdapterPosition() == 0) {
    newOffset=top;
    runShadowAnimation(0,false);
  }
 else {
    runShadowAnimation(0,true);
  }
  if (scrollOffsetY != newOffset) {
    gridView.setTopGlowOffset(scrollOffsetY=newOffset);
    frameLayout.setTranslationY(scrollOffsetY);
    searchEmptyView.setTranslationY(scrollOffsetY);
    containerView.invalidate();
  }
}
