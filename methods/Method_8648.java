public void onOpen(boolean forceEmoji){
  if (currentPage != 0 && currentChatId != 0) {
    currentPage=0;
  }
  if (currentPage == 0 || forceEmoji || views.size() == 1) {
    showBackspaceButton(true,false);
    showStickerSettingsButton(false,false);
    if (pager.getCurrentItem() != 0) {
      pager.setCurrentItem(0,!forceEmoji);
    }
  }
 else   if (currentPage == 1) {
    showBackspaceButton(false,false);
    showStickerSettingsButton(true,false);
    if (pager.getCurrentItem() != 2) {
      pager.setCurrentItem(2,false);
    }
    if (stickersTab != null) {
      if (trendingTabNum == 0 && DataQuery.getInstance(currentAccount).areAllTrendingStickerSetsUnread()) {
        showTrendingTab(true);
      }
 else       if (recentTabBum >= 0) {
        stickersTab.selectTab(recentTabBum);
      }
 else       if (favTabBum >= 0) {
        stickersTab.selectTab(favTabBum);
      }
 else {
        stickersTab.selectTab(stickersTabOffset);
      }
    }
  }
 else   if (currentPage == 2) {
    showBackspaceButton(false,false);
    showStickerSettingsButton(false,false);
    if (pager.getCurrentItem() != 1) {
      pager.setCurrentItem(1,false);
    }
  }
}
