private void checkStickersScroll(int firstVisibleItem){
  if (stickersGridView == null) {
    return;
  }
  scrollSlidingTabStrip.onPageScrolled(stickersGridAdapter.getTabForPosition(firstVisibleItem) + 1,(recentTabBum > 0 ? recentTabBum : stickersTabOffset) + 1);
}
