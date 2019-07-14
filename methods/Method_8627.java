public void showSearchField(boolean show){
  GridLayoutManager layoutManager;
  ScrollSlidingTabStrip tabStrip;
  for (int a=0; a < 3; a++) {
    if (a == 0) {
      layoutManager=emojiLayoutManager;
      tabStrip=emojiTabs;
    }
 else     if (a == 1) {
      layoutManager=gifLayoutManager;
      tabStrip=null;
    }
 else {
      layoutManager=stickersLayoutManager;
      tabStrip=stickersTab;
    }
    if (layoutManager == null) {
      continue;
    }
    int position=layoutManager.findFirstVisibleItemPosition();
    if (show) {
      if (position == 1 || position == 2) {
        layoutManager.scrollToPosition(0);
        if (tabStrip != null) {
          tabStrip.setTranslationY(0);
        }
      }
    }
 else {
      if (position == 0) {
        layoutManager.scrollToPositionWithOffset(1,0);
      }
    }
  }
}
