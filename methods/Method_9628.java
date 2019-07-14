private void updateRows(){
  rowCount=0;
  if (currentType == DataQuery.TYPE_IMAGE) {
    suggestRow=rowCount++;
    featuredRow=rowCount++;
    featuredInfoRow=rowCount++;
    masksRow=rowCount++;
    masksInfoRow=rowCount++;
  }
 else {
    featuredRow=-1;
    featuredInfoRow=-1;
    masksRow=-1;
    masksInfoRow=-1;
  }
  if (DataQuery.getInstance(currentAccount).getArchivedStickersCount(currentType) != 0) {
    archivedRow=rowCount++;
    archivedInfoRow=rowCount++;
  }
 else {
    archivedRow=-1;
    archivedInfoRow=-1;
  }
  ArrayList<TLRPC.TL_messages_stickerSet> stickerSets=DataQuery.getInstance(currentAccount).getStickerSets(currentType);
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
}
