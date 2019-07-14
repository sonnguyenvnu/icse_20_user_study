private void processLoadedStickers(final int type,final ArrayList<TLRPC.TL_messages_stickerSet> res,final boolean cache,final int date,final int hash){
  AndroidUtilities.runOnUIThread(() -> {
    loadingStickers[type]=false;
    stickersLoaded[type]=true;
  }
);
  Utilities.stageQueue.postRunnable(() -> {
    if (cache && (res == null || Math.abs(System.currentTimeMillis() / 1000 - date) >= 60 * 60) || !cache && res == null && hash == 0) {
      AndroidUtilities.runOnUIThread(() -> {
        if (res != null && hash != 0) {
          loadHash[type]=hash;
        }
        loadStickers(type,false,false);
      }
,res == null && !cache ? 1000 : 0);
      if (res == null) {
        return;
      }
    }
    if (res != null) {
      try {
        final ArrayList<TLRPC.TL_messages_stickerSet> stickerSetsNew=new ArrayList<>();
        final LongSparseArray<TLRPC.TL_messages_stickerSet> stickerSetsByIdNew=new LongSparseArray<>();
        final HashMap<String,TLRPC.TL_messages_stickerSet> stickerSetsByNameNew=new HashMap<>();
        final LongSparseArray<String> stickersByEmojiNew=new LongSparseArray<>();
        final LongSparseArray<TLRPC.Document> stickersByIdNew=new LongSparseArray<>();
        final HashMap<String,ArrayList<TLRPC.Document>> allStickersNew=new HashMap<>();
        for (int a=0; a < res.size(); a++) {
          TLRPC.TL_messages_stickerSet stickerSet=res.get(a);
          if (stickerSet == null) {
            continue;
          }
          stickerSetsNew.add(stickerSet);
          stickerSetsByIdNew.put(stickerSet.set.id,stickerSet);
          stickerSetsByNameNew.put(stickerSet.set.short_name,stickerSet);
          for (int b=0; b < stickerSet.documents.size(); b++) {
            TLRPC.Document document=stickerSet.documents.get(b);
            if (document == null || document instanceof TLRPC.TL_documentEmpty) {
              continue;
            }
            stickersByIdNew.put(document.id,document);
          }
          if (!stickerSet.set.archived) {
            for (int b=0; b < stickerSet.packs.size(); b++) {
              TLRPC.TL_stickerPack stickerPack=stickerSet.packs.get(b);
              if (stickerPack == null || stickerPack.emoticon == null) {
                continue;
              }
              stickerPack.emoticon=stickerPack.emoticon.replace("\uFE0F","");
              ArrayList<TLRPC.Document> arrayList=allStickersNew.get(stickerPack.emoticon);
              if (arrayList == null) {
                arrayList=new ArrayList<>();
                allStickersNew.put(stickerPack.emoticon,arrayList);
              }
              for (int c=0; c < stickerPack.documents.size(); c++) {
                Long id=stickerPack.documents.get(c);
                if (stickersByEmojiNew.indexOfKey(id) < 0) {
                  stickersByEmojiNew.put(id,stickerPack.emoticon);
                }
                TLRPC.Document sticker=stickersByIdNew.get(id);
                if (sticker != null) {
                  arrayList.add(sticker);
                }
              }
            }
          }
        }
        if (!cache) {
          putStickersToCache(type,stickerSetsNew,date,hash);
        }
        AndroidUtilities.runOnUIThread(() -> {
          for (int a=0; a < stickerSets[type].size(); a++) {
            TLRPC.StickerSet set=stickerSets[type].get(a).set;
            stickerSetsById.remove(set.id);
            installedStickerSetsById.remove(set.id);
            stickerSetsByName.remove(set.short_name);
          }
          for (int a=0; a < stickerSetsByIdNew.size(); a++) {
            stickerSetsById.put(stickerSetsByIdNew.keyAt(a),stickerSetsByIdNew.valueAt(a));
            if (type != TYPE_FEATURED) {
              installedStickerSetsById.put(stickerSetsByIdNew.keyAt(a),stickerSetsByIdNew.valueAt(a));
            }
          }
          stickerSetsByName.putAll(stickerSetsByNameNew);
          stickerSets[type]=stickerSetsNew;
          loadHash[type]=hash;
          loadDate[type]=date;
          if (type == TYPE_IMAGE) {
            allStickers=allStickersNew;
            stickersByEmoji=stickersByEmojiNew;
          }
 else           if (type == TYPE_FEATURED) {
            allStickersFeatured=allStickersNew;
          }
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.stickersDidLoad,type);
        }
);
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
 else     if (!cache) {
      AndroidUtilities.runOnUIThread(() -> loadDate[type]=date);
      putStickersToCache(type,null,date,0);
    }
  }
);
}
