protected void processLoadedRecentDocuments(final int type,final ArrayList<TLRPC.Document> documents,final boolean gif,final int date,boolean replace){
  if (documents != null) {
    MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
      try {
        SQLiteDatabase database=MessagesStorage.getInstance(currentAccount).getDatabase();
        int maxCount;
        if (gif) {
          maxCount=MessagesController.getInstance(currentAccount).maxRecentGifsCount;
        }
 else {
          if (type == TYPE_FAVE) {
            maxCount=MessagesController.getInstance(currentAccount).maxFaveStickersCount;
          }
 else {
            maxCount=MessagesController.getInstance(currentAccount).maxRecentStickersCount;
          }
        }
        database.beginTransaction();
        SQLitePreparedStatement state=database.executeFast("REPLACE INTO web_recent_v3 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        int count=documents.size();
        int cacheType;
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
        if (replace) {
          database.executeFast("DELETE FROM web_recent_v3 WHERE type = " + cacheType).stepThis().dispose();
        }
        for (int a=0; a < count; a++) {
          if (a == maxCount) {
            break;
          }
          TLRPC.Document document=documents.get(a);
          state.requery();
          state.bindString(1,"" + document.id);
          state.bindInteger(2,cacheType);
          state.bindString(3,"");
          state.bindString(4,"");
          state.bindString(5,"");
          state.bindInteger(6,0);
          state.bindInteger(7,0);
          state.bindInteger(8,0);
          state.bindInteger(9,date != 0 ? date : count - a);
          NativeByteBuffer data=new NativeByteBuffer(document.getObjectSize());
          document.serializeToStream(data);
          state.bindByteBuffer(10,data);
          state.step();
          if (data != null) {
            data.reuse();
          }
        }
        state.dispose();
        database.commitTransaction();
        if (documents.size() >= maxCount) {
          database.beginTransaction();
          for (int a=maxCount; a < documents.size(); a++) {
            database.executeFast("DELETE FROM web_recent_v3 WHERE id = '" + documents.get(a).id + "' AND type = " + cacheType).stepThis().dispose();
          }
          database.commitTransaction();
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
  }
  if (date == 0) {
    AndroidUtilities.runOnUIThread(() -> {
      SharedPreferences.Editor editor=MessagesController.getEmojiSettings(currentAccount).edit();
      if (gif) {
        loadingRecentGifs=false;
        recentGifsLoaded=true;
        editor.putLong("lastGifLoadTime",System.currentTimeMillis()).commit();
      }
 else {
        loadingRecentStickers[type]=false;
        recentStickersLoaded[type]=true;
        if (type == TYPE_IMAGE) {
          editor.putLong("lastStickersLoadTime",System.currentTimeMillis()).commit();
        }
 else         if (type == TYPE_MASK) {
          editor.putLong("lastStickersLoadTimeMask",System.currentTimeMillis()).commit();
        }
 else {
          editor.putLong("lastStickersLoadTimeFavs",System.currentTimeMillis()).commit();
        }
      }
      if (documents != null) {
        if (gif) {
          recentGifs=documents;
        }
 else {
          recentStickers[type]=documents;
        }
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.recentDocumentsDidLoad,gif,type);
      }
 else {
      }
    }
);
  }
}
