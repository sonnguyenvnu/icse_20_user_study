public void loadStickers(final int type,boolean cache,boolean force){
  if (loadingStickers[type]) {
    return;
  }
  if (type == TYPE_FEATURED) {
    if (featuredStickerSets.isEmpty() || !MessagesController.getInstance(currentAccount).preloadFeaturedStickers) {
      return;
    }
  }
 else {
    loadArchivedStickersCount(type,cache);
  }
  loadingStickers[type]=true;
  if (cache) {
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      ArrayList<TLRPC.TL_messages_stickerSet> newStickerArray=null;
      int date=0;
      int hash=0;
      SQLiteCursor cursor=null;
      try {
        cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT data, date, hash FROM stickers_v2 WHERE id = " + (type + 1));
        if (cursor.next()) {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            newStickerArray=new ArrayList<>();
            int count=data.readInt32(false);
            for (int a=0; a < count; a++) {
              TLRPC.TL_messages_stickerSet stickerSet=TLRPC.TL_messages_stickerSet.TLdeserialize(data,data.readInt32(false),false);
              newStickerArray.add(stickerSet);
            }
            data.reuse();
          }
          date=cursor.intValue(1);
          hash=calcStickersHash(newStickerArray);
        }
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
 finally {
        if (cursor != null) {
          cursor.dispose();
        }
      }
      processLoadedStickers(type,newStickerArray,true,date,hash);
    }
);
  }
 else {
    if (type == TYPE_FEATURED) {
      TLRPC.TL_messages_allStickers response=new TLRPC.TL_messages_allStickers();
      response.hash=loadFeaturedHash;
      for (int a=0, size=featuredStickerSets.size(); a < size; a++) {
        response.sets.add(featuredStickerSets.get(a).set);
      }
      processLoadStickersResponse(type,response);
      return;
    }
    TLObject req;
    final int hash;
    if (type == TYPE_IMAGE) {
      req=new TLRPC.TL_messages_getAllStickers();
      hash=((TLRPC.TL_messages_getAllStickers)req).hash=force ? 0 : loadHash[type];
    }
 else {
      req=new TLRPC.TL_messages_getMaskStickers();
      hash=((TLRPC.TL_messages_getMaskStickers)req).hash=force ? 0 : loadHash[type];
    }
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      if (response instanceof TLRPC.TL_messages_allStickers) {
        processLoadStickersResponse(type,(TLRPC.TL_messages_allStickers)response);
      }
 else {
        processLoadedStickers(type,null,false,(int)(System.currentTimeMillis() / 1000),hash);
      }
    }
));
  }
}
