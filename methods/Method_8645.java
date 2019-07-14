public void addRecentSticker(TLRPC.Document document){
  if (document == null) {
    return;
  }
  DataQuery.getInstance(currentAccount).addRecentSticker(DataQuery.TYPE_IMAGE,null,document,(int)(System.currentTimeMillis() / 1000),false);
  boolean wasEmpty=recentStickers.isEmpty();
  recentStickers=DataQuery.getInstance(currentAccount).getRecentStickers(DataQuery.TYPE_IMAGE);
  if (stickersGridAdapter != null) {
    stickersGridAdapter.notifyDataSetChanged();
  }
  if (wasEmpty) {
    updateStickerTabs();
  }
}
