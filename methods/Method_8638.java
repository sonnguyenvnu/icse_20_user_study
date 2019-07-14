private void showTrendingTab(boolean show){
  if (show) {
    trendingGridView.setVisibility(VISIBLE);
    stickersGridView.setVisibility(GONE);
    stickersSearchField.setVisibility(GONE);
    stickersTab.onPageScrolled(trendingTabNum,recentTabBum > 0 ? recentTabBum : stickersTabOffset);
    saveNewPage();
  }
 else {
    trendingGridView.setVisibility(GONE);
    stickersGridView.setVisibility(VISIBLE);
    stickersSearchField.setVisibility(VISIBLE);
  }
}
