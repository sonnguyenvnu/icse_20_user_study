private void updateRows(){
  rowCount=0;
  ArrayList<TLRPC.StickerSetCovered> stickerSets=DataQuery.getInstance(currentAccount).getFeaturedStickerSets();
  if (!stickerSets.isEmpty()) {
    stickersStartRow=rowCount;
    stickersEndRow=rowCount + stickerSets.size();
    rowCount+=stickerSets.size();
    stickersShadowRow=rowCount++;
  }
 else {
    stickersStartRow=-1;
    stickersEndRow=-1;
    stickersShadowRow=-1;
  }
  if (listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
  DataQuery.getInstance(currentAccount).markFaturedStickersAsRead(true);
}
