private void checkPanels(){
  if (scrollSlidingTabStrip == null) {
    return;
  }
  int position=stickersLayoutManager.findFirstVisibleItemPosition();
  if (position != RecyclerView.NO_POSITION) {
    scrollSlidingTabStrip.onPageScrolled(stickersGridAdapter.getTabForPosition(position) + 1,(recentTabBum > 0 ? recentTabBum : stickersTabOffset) + 1);
  }
}
