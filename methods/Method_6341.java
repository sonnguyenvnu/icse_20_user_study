public boolean areAllTrendingStickerSetsUnread(){
  for (int a=0, N=featuredStickerSets.size(); a < N; a++) {
    TLRPC.StickerSetCovered pack=featuredStickerSets.get(a);
    if (DataQuery.getInstance(currentAccount).isStickerPackInstalled(pack.set.id) || pack.covers.isEmpty() && pack.cover == null) {
      continue;
    }
    if (!unreadStickerSets.contains(pack.set.id)) {
      return false;
    }
  }
  return true;
}
