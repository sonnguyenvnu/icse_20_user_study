void jumpToPositionForSmoothScroller(int position){
  if (mLayout == null) {
    return;
  }
  setScrollState(SCROLL_STATE_SETTLING);
  mLayout.scrollToPosition(position);
  awakenScrollBars();
}
