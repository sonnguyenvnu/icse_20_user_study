private void checkScroll(){
  int firstVisibleItem=stickersLayoutManager.findFirstVisibleItemPosition();
  if (firstVisibleItem == RecyclerView.NO_POSITION) {
    return;
  }
  if (stickersGridView == null) {
    return;
  }
  int firstTab;
  if (favTabBum > 0) {
    firstTab=favTabBum;
  }
 else   if (recentTabBum > 0) {
    firstTab=recentTabBum;
  }
 else {
    firstTab=stickersTabOffset;
  }
  stickersTab.onPageScrolled(stickersGridAdapter.getTabForPosition(firstVisibleItem),firstTab);
}
