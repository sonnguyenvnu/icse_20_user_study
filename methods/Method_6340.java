public ArrayList<TLRPC.TL_messages_stickerSet> getStickerSets(int type){
  if (type == TYPE_FEATURED) {
    return stickerSets[2];
  }
 else {
    return stickerSets[type];
  }
}
