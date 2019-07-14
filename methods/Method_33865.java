public void setLoadMoreGone(){
  if (mFootViews == null) {
    return;
  }
  View footView=mFootViews.get(0);
  if (footView != null && footView instanceof LoadingMoreFooter) {
    mFootViews.remove(0);
  }
}
