public void addNewStickerSet(final TLRPC.TL_messages_stickerSet set){
  if (stickerSetsById.indexOfKey(set.set.id) >= 0 || stickerSetsByName.containsKey(set.set.short_name)) {
    return;
  }
  int type=set.set.masks ? TYPE_MASK : TYPE_IMAGE;
  stickerSets[type].add(0,set);
  stickerSetsById.put(set.set.id,set);
  installedStickerSetsById.put(set.set.id,set);
  stickerSetsByName.put(set.set.short_name,set);
  LongSparseArray<TLRPC.Document> stickersById=new LongSparseArray<>();
  for (int a=0; a < set.documents.size(); a++) {
    TLRPC.Document document=set.documents.get(a);
    stickersById.put(document.id,document);
  }
  for (int a=0; a < set.packs.size(); a++) {
    TLRPC.TL_stickerPack stickerPack=set.packs.get(a);
    stickerPack.emoticon=stickerPack.emoticon.replace("\uFE0F","");
    ArrayList<TLRPC.Document> arrayList=allStickers.get(stickerPack.emoticon);
    if (arrayList == null) {
      arrayList=new ArrayList<>();
      allStickers.put(stickerPack.emoticon,arrayList);
    }
    for (int c=0; c < stickerPack.documents.size(); c++) {
      Long id=stickerPack.documents.get(c);
      if (stickersByEmoji.indexOfKey(id) < 0) {
        stickersByEmoji.put(id,stickerPack.emoticon);
      }
      TLRPC.Document sticker=stickersById.get(id);
      if (sticker != null) {
        arrayList.add(sticker);
      }
    }
  }
  loadHash[type]=calcStickersHash(stickerSets[type]);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.stickersDidLoad,type);
  loadStickers(type,false,true);
}
