public void setCurrentItem(int item,boolean smoothScroll){
  if (mRecyclerView == null) {
    return;
  }
  if (smoothScroll) {
    mRecyclerView.smoothScrollToPosition(item);
  }
 else {
    scrollToPosition(item);
  }
}
