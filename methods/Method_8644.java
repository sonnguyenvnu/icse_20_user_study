private void checkPanels(){
  if (stickersTab == null) {
    return;
  }
  if (trendingTabNum == -2 && trendingGridView != null && trendingGridView.getVisibility() == VISIBLE) {
    trendingGridView.setVisibility(GONE);
    stickersGridView.setVisibility(VISIBLE);
    stickersSearchField.setVisibility(VISIBLE);
  }
  if (trendingGridView != null && trendingGridView.getVisibility() == VISIBLE) {
    stickersTab.onPageScrolled(trendingTabNum,recentTabBum > 0 ? recentTabBum : stickersTabOffset);
  }
 else {
    int position=stickersLayoutManager.findFirstVisibleItemPosition();
    if (position != RecyclerView.NO_POSITION) {
      int firstTab;
      if (favTabBum > 0) {
        firstTab=favTabBum;
      }
 else       if (recentTabBum > 0) {
        firstTab=recentTabBum;
      }
 else {
        firstTab=stickersTabOffset;
      }
      stickersTab.onPageScrolled(stickersGridAdapter.getTabForPosition(position),firstTab);
    }
  }
}
