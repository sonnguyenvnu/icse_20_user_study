private void updateVisibleTrendingSets(){
  if (trendingGridAdapter == null || trendingGridAdapter == null) {
    return;
  }
  try {
    for (int b=0; b < 2; b++) {
      RecyclerListView gridView=b == 0 ? trendingGridView : stickersGridView;
      int count=gridView.getChildCount();
      for (int a=0; a < count; a++) {
        View child=gridView.getChildAt(a);
        if (child instanceof FeaturedStickerSetInfoCell) {
          RecyclerListView.Holder holder=(RecyclerListView.Holder)gridView.getChildViewHolder(child);
          if (holder == null) {
            continue;
          }
          FeaturedStickerSetInfoCell cell=(FeaturedStickerSetInfoCell)child;
          ArrayList<Long> unreadStickers=DataQuery.getInstance(currentAccount).getUnreadStickerSets();
          TLRPC.StickerSetCovered stickerSetCovered=cell.getStickerSet();
          boolean unread=unreadStickers != null && unreadStickers.contains(stickerSetCovered.set.id);
          cell.setStickerSet(stickerSetCovered,unread);
          if (unread) {
            DataQuery.getInstance(currentAccount).markFaturedStickersByIdAsRead(stickerSetCovered.set.id);
          }
          boolean installing=installingStickerSets.indexOfKey(stickerSetCovered.set.id) >= 0;
          boolean removing=removingStickerSets.indexOfKey(stickerSetCovered.set.id) >= 0;
          if (installing || removing) {
            if (installing && cell.isInstalled()) {
              installingStickerSets.remove(stickerSetCovered.set.id);
              installing=false;
            }
 else             if (removing && !cell.isInstalled()) {
              removingStickerSets.remove(stickerSetCovered.set.id);
              removing=false;
            }
          }
          cell.setDrawProgress(installing || removing);
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
