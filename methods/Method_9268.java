private void updateRows(){
  rowCount=0;
  nameRow=rowCount++;
  if (selectedStickerSet != null || searchWas) {
    selectedStickerRow=rowCount++;
  }
 else {
    selectedStickerRow=-1;
  }
  infoRow=rowCount++;
  ArrayList<TLRPC.TL_messages_stickerSet> stickerSets=DataQuery.getInstance(currentAccount).getStickerSets(DataQuery.TYPE_IMAGE);
  if (!stickerSets.isEmpty()) {
    headerRow=rowCount++;
    stickersStartRow=rowCount;
    stickersEndRow=rowCount + stickerSets.size();
    rowCount+=stickerSets.size();
    stickersShadowRow=rowCount++;
  }
 else {
    headerRow=-1;
    stickersStartRow=-1;
    stickersEndRow=-1;
    stickersShadowRow=-1;
  }
  if (nameContainer != null) {
    nameContainer.invalidate();
  }
  if (listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
}
