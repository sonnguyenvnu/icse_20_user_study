public void loadFeaturedStickers(boolean cache,boolean force){
  if (loadingFeaturedStickers) {
    return;
  }
  loadingFeaturedStickers=true;
  if (cache) {
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      ArrayList<TLRPC.StickerSetCovered> newStickerArray=null;
      ArrayList<Long> unread=new ArrayList<>();
      int date=0;
      int hash=0;
      SQLiteCursor cursor=null;
      try {
        cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT data, unread, date, hash FROM stickers_featured WHERE 1");
        if (cursor.next()) {
          NativeByteBuffer data=cursor.byteBufferValue(0);
          if (data != null) {
            newStickerArray=new ArrayList<>();
            int count=data.readInt32(false);
            for (int a=0; a < count; a++) {
              TLRPC.StickerSetCovered stickerSet=TLRPC.StickerSetCovered.TLdeserialize(data,data.readInt32(false),false);
              newStickerArray.add(stickerSet);
            }
            data.reuse();
          }
          data=cursor.byteBufferValue(1);
          if (data != null) {
            int count=data.readInt32(false);
            for (int a=0; a < count; a++) {
              unread.add(data.readInt64(false));
            }
            data.reuse();
          }
          date=cursor.intValue(2);
          hash=calcFeaturedStickersHash(newStickerArray);
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
      processLoadedFeaturedStickers(newStickerArray,unread,true,date,hash);
    }
);
  }
 else {
    final TLRPC.TL_messages_getFeaturedStickers req=new TLRPC.TL_messages_getFeaturedStickers();
    req.hash=force ? 0 : loadFeaturedHash;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      if (response instanceof TLRPC.TL_messages_featuredStickers) {
        TLRPC.TL_messages_featuredStickers res=(TLRPC.TL_messages_featuredStickers)response;
        processLoadedFeaturedStickers(res.sets,res.unread,false,(int)(System.currentTimeMillis() / 1000),res.hash);
      }
 else {
        processLoadedFeaturedStickers(null,null,false,(int)(System.currentTimeMillis() / 1000),req.hash);
      }
    }
));
  }
}
