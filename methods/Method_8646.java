public void addRecentGif(TLRPC.Document document){
  if (document == null) {
    return;
  }
  boolean wasEmpty=recentGifs.isEmpty();
  recentGifs=DataQuery.getInstance(currentAccount).getRecentGifs();
  if (gifAdapter != null) {
    gifAdapter.notifyDataSetChanged();
  }
  if (wasEmpty) {
    updateStickerTabs();
  }
}
