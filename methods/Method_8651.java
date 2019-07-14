private void checkDocuments(boolean isGif){
  if (isGif) {
    recentGifs=DataQuery.getInstance(currentAccount).getRecentGifs();
    if (gifAdapter != null) {
      gifAdapter.notifyDataSetChanged();
    }
  }
 else {
    int previousCount=recentStickers.size();
    int previousCount2=favouriteStickers.size();
    recentStickers=DataQuery.getInstance(currentAccount).getRecentStickers(DataQuery.TYPE_IMAGE);
    favouriteStickers=DataQuery.getInstance(currentAccount).getRecentStickers(DataQuery.TYPE_FAVE);
    for (int a=0; a < favouriteStickers.size(); a++) {
      TLRPC.Document favSticker=favouriteStickers.get(a);
      for (int b=0; b < recentStickers.size(); b++) {
        TLRPC.Document recSticker=recentStickers.get(b);
        if (recSticker.dc_id == favSticker.dc_id && recSticker.id == favSticker.id) {
          recentStickers.remove(b);
          break;
        }
      }
    }
    if (previousCount != recentStickers.size() || previousCount2 != favouriteStickers.size()) {
      updateStickerTabs();
    }
    if (stickersGridAdapter != null) {
      stickersGridAdapter.notifyDataSetChanged();
    }
    checkPanels();
  }
}
