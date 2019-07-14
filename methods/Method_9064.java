@Override public void setVisibility(int visibility){
  super.setVisibility(visibility);
  if (visibility != GONE) {
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.stickersDidLoad);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.recentDocumentsDidLoad);
    updateStickerTabs();
    reloadStickersAdapter();
    checkDocuments();
    DataQuery.getInstance(currentAccount).loadRecents(DataQuery.TYPE_IMAGE,false,true,false);
    DataQuery.getInstance(currentAccount).loadRecents(DataQuery.TYPE_MASK,false,true,false);
    DataQuery.getInstance(currentAccount).loadRecents(DataQuery.TYPE_FAVE,false,true,false);
  }
}
