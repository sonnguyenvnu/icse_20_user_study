private void updateRows(){
  rowCount=0;
  if (!sets.isEmpty()) {
    stickersStartRow=rowCount;
    stickersEndRow=rowCount + sets.size();
    rowCount+=sets.size();
    if (!endReached) {
      stickersLoadingRow=rowCount++;
      stickersShadowRow=-1;
    }
 else {
      stickersShadowRow=rowCount++;
      stickersLoadingRow=-1;
    }
  }
 else {
    stickersStartRow=-1;
    stickersEndRow=-1;
    stickersLoadingRow=-1;
    stickersShadowRow=-1;
  }
  if (listAdapter != null) {
    listAdapter.notifyDataSetChanged();
  }
}
