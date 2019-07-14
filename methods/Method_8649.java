@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  if (visibility != GONE) {
    Emoji.sortEmoji();
    emojiAdapter.notifyDataSetChanged();
    if (stickersGridAdapter != null) {
      NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.stickersDidLoad);
      NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.recentDocumentsDidLoad);
      updateStickerTabs();
      reloadStickersAdapter();
    }
    if (trendingGridAdapter != null) {
      trendingLoaded=false;
      trendingGridAdapter.notifyDataSetChanged();
    }
    checkDocuments(true);
    checkDocuments(false);
    DataQuery.getInstance(currentAccount).loadRecents(DataQuery.TYPE_IMAGE,true,true,false);
    DataQuery.getInstance(currentAccount).loadRecents(DataQuery.TYPE_IMAGE,false,true,false);
    DataQuery.getInstance(currentAccount).loadRecents(DataQuery.TYPE_FAVE,false,true,false);
  }
}
