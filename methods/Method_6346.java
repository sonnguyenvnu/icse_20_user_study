public void loadRecents(final int type,final boolean gif,boolean cache,boolean force){
  if (gif) {
    if (loadingRecentGifs) {
      return;
    }
    loadingRecentGifs=true;
    if (recentGifsLoaded) {
      cache=false;
    }
  }
 else {
    if (loadingRecentStickers[type]) {
      return;
    }
    loadingRecentStickers[type]=true;
    if (recentStickersLoaded[type]) {
      cache=false;
    }
  }
  if (cache) {
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      try {
        final int cacheType;
        if (gif) {
          cacheType=2;
        }
 else         if (type == TYPE_IMAGE) {
          cacheType=3;
        }
 else         if (type == TYPE_MASK) {
          cacheType=4;
        }
 else {
          cacheType=5;
        }
        SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT document FROM web_recent_v3 WHERE type = " + cacheType + " ORDER BY date DESC");
        final ArrayList<TLRPC.Document> arrayList=new ArrayList<>();
        while (cursor.next()) {
          if (!cursor.isNull(0)) {
            NativeByteBuffer data=cursor.byteBufferValue(0);
            if (data != null) {
              TLRPC.Document document=TLRPC.Document.TLdeserialize(data,data.readInt32(false),false);
              if (document != null) {
                arrayList.add(document);
              }
              data.reuse();
            }
          }
        }
        cursor.dispose();
        AndroidUtilities.runOnUIThread(() -> {
          if (gif) {
            recentGifs=arrayList;
            loadingRecentGifs=false;
            recentGifsLoaded=true;
          }
 else {
            recentStickers[type]=arrayList;
            loadingRecentStickers[type]=false;
            recentStickersLoaded[type]=true;
          }
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.recentDocumentsDidLoad,gif,type);
          loadRecents(type,gif,false,false);
        }
);
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
);
  }
 else {
    SharedPreferences preferences=MessagesController.getEmojiSettings(currentAccount);
    if (!force) {
      long lastLoadTime;
      if (gif) {
        lastLoadTime=preferences.getLong("lastGifLoadTime",0);
      }
 else       if (type == TYPE_IMAGE) {
        lastLoadTime=preferences.getLong("lastStickersLoadTime",0);
      }
 else       if (type == TYPE_MASK) {
        lastLoadTime=preferences.getLong("lastStickersLoadTimeMask",0);
      }
 else {
        lastLoadTime=preferences.getLong("lastStickersLoadTimeFavs",0);
      }
      if (Math.abs(System.currentTimeMillis() - lastLoadTime) < 60 * 60 * 1000) {
        if (gif) {
          loadingRecentGifs=false;
        }
 else {
          loadingRecentStickers[type]=false;
        }
        return;
      }
    }
    if (gif) {
      TLRPC.TL_messages_getSavedGifs req=new TLRPC.TL_messages_getSavedGifs();
      req.hash=calcDocumentsHash(recentGifs);
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
        ArrayList<TLRPC.Document> arrayList=null;
        if (response instanceof TLRPC.TL_messages_savedGifs) {
          TLRPC.TL_messages_savedGifs res=(TLRPC.TL_messages_savedGifs)response;
          arrayList=res.gifs;
        }
        processLoadedRecentDocuments(type,arrayList,gif,0,true);
      }
);
    }
 else {
      TLObject request;
      if (type == TYPE_FAVE) {
        TLRPC.TL_messages_getFavedStickers req=new TLRPC.TL_messages_getFavedStickers();
        req.hash=calcDocumentsHash(recentStickers[type]);
        request=req;
      }
 else {
        TLRPC.TL_messages_getRecentStickers req=new TLRPC.TL_messages_getRecentStickers();
        req.hash=calcDocumentsHash(recentStickers[type]);
        req.attached=type == TYPE_MASK;
        request=req;
      }
      ConnectionsManager.getInstance(currentAccount).sendRequest(request,(response,error) -> {
        ArrayList<TLRPC.Document> arrayList=null;
        if (type == TYPE_FAVE) {
          if (response instanceof TLRPC.TL_messages_favedStickers) {
            TLRPC.TL_messages_favedStickers res=(TLRPC.TL_messages_favedStickers)response;
            arrayList=res.stickers;
          }
        }
 else {
          if (response instanceof TLRPC.TL_messages_recentStickers) {
            TLRPC.TL_messages_recentStickers res=(TLRPC.TL_messages_recentStickers)response;
            arrayList=res.stickers;
          }
        }
        processLoadedRecentDocuments(type,arrayList,gif,0,true);
      }
);
    }
  }
}
