public void scrollTo(int scroll){
  if (mScroll == scroll) {
    return;
  }
  mHeaderView.scrollTo(scroll);
  scroll=Math.max(0,scroll - mHeaderView.getScroll());
  mContentView.scrollTo(scroll);
  int headerScroll=mHeaderView.getScroll();
  mScroll=headerScroll + mContentView.getScroll();
  if (headerScroll == 0) {
    mHeaderCollapsed=false;
  }
 else   if (headerScroll == mHeaderView.getScrollExtent()) {
    mHeaderCollapsed=true;
  }
}
