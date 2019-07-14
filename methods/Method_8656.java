@SuppressWarnings("unchecked") @Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.stickersDidLoad) {
    if ((Integer)args[0] == DataQuery.TYPE_IMAGE) {
      if (trendingGridAdapter != null) {
        if (trendingLoaded) {
          updateVisibleTrendingSets();
        }
 else {
          trendingGridAdapter.notifyDataSetChanged();
        }
      }
      updateStickerTabs();
      reloadStickersAdapter();
      checkPanels();
    }
  }
 else   if (id == NotificationCenter.recentDocumentsDidLoad) {
    boolean isGif=(Boolean)args[0];
    int type=(Integer)args[1];
    if (isGif || type == DataQuery.TYPE_IMAGE || type == DataQuery.TYPE_FAVE) {
      checkDocuments(isGif);
    }
  }
 else   if (id == NotificationCenter.featuredStickersDidLoad) {
    if (trendingGridAdapter != null) {
      if (featuredStickersHash != DataQuery.getInstance(currentAccount).getFeaturesStickersHashWithoutUnread()) {
        trendingLoaded=false;
      }
      if (trendingLoaded) {
        updateVisibleTrendingSets();
      }
 else {
        trendingGridAdapter.notifyDataSetChanged();
      }
    }
    if (typeTabs != null) {
      int count=typeTabs.getChildCount();
      for (int a=0; a < count; a++) {
        typeTabs.getChildAt(a).invalidate();
      }
    }
    updateStickerTabs();
  }
 else   if (id == NotificationCenter.groupStickersDidLoad) {
    if (info != null && info.stickerset != null && info.stickerset.id == (Long)args[0]) {
      updateStickerTabs();
    }
  }
 else   if (id == NotificationCenter.emojiDidLoad) {
    if (stickersGridView != null) {
      int count=stickersGridView.getChildCount();
      for (int a=0; a < count; a++) {
        View child=stickersGridView.getChildAt(a);
        if (child instanceof StickerSetNameCell || child instanceof StickerEmojiCell) {
          child.invalidate();
        }
      }
    }
  }
 else   if (id == NotificationCenter.newEmojiSuggestionsAvailable) {
    if (emojiGridView != null && needEmojiSearch && (emojiSearchField.progressDrawable.isAnimating() || emojiGridView.getAdapter() == emojiSearchAdapter) && !TextUtils.isEmpty(emojiSearchAdapter.lastSearchEmojiString)) {
      emojiSearchAdapter.search(emojiSearchAdapter.lastSearchEmojiString);
    }
  }
}
