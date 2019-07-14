public void setInfo(TLRPC.ChatFull chatFull){
  info=chatFull;
  if (info != null && info.stickerset != null) {
    selectedStickerSet=DataQuery.getInstance(currentAccount).getGroupStickerSetById(info.stickerset);
  }
}
