public void addRecentSticker(TLRPC.Document document){
  if (document == null) {
    return;
  }
  DataQuery.getInstance(currentAccount).addRecentSticker(currentType,null,document,(int)(System.currentTimeMillis() / 1000),false);
  boolean wasEmpty=recentStickers[currentType].isEmpty();
  recentStickers[currentType]=DataQuery.getInstance(currentAccount).getRecentStickers(currentType);
  if (stickersGridAdapter != null) {
    stickersGridAdapter.notifyDataSetChanged();
  }
  if (wasEmpty) {
    updateStickerTabs();
  }
}
