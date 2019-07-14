@Override public void onScrollStateChanged(int state){
  if (state == RecyclerView.SCROLL_STATE_IDLE) {
    checkForGaps();
  }
}
