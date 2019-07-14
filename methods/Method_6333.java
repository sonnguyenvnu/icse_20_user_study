public boolean isStickerInFavorites(TLRPC.Document document){
  for (int a=0; a < recentStickers[TYPE_FAVE].size(); a++) {
    TLRPC.Document d=recentStickers[TYPE_FAVE].get(a);
    if (d.id == document.id && d.dc_id == document.dc_id) {
      return true;
    }
  }
  return false;
}
