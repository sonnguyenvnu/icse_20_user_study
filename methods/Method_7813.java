private void putRecentHashtags(final ArrayList<HashtagObject> arrayList){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      MessagesStorage.getInstance(currentAccount).getDatabase().beginTransaction();
      SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO hashtag_recent_v2 VALUES(?, ?)");
      for (int a=0; a < arrayList.size(); a++) {
        if (a == 100) {
          break;
        }
        HashtagObject hashtagObject=arrayList.get(a);
        state.requery();
        state.bindString(1,hashtagObject.hashtag);
        state.bindInteger(2,hashtagObject.date);
        state.step();
      }
      state.dispose();
      MessagesStorage.getInstance(currentAccount).getDatabase().commitTransaction();
      if (arrayList.size() >= 100) {
        MessagesStorage.getInstance(currentAccount).getDatabase().beginTransaction();
        for (int a=100; a < arrayList.size(); a++) {
          MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM hashtag_recent_v2 WHERE id = '" + arrayList.get(a).hashtag + "'").stepThis().dispose();
        }
        MessagesStorage.getInstance(currentAccount).getDatabase().commitTransaction();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
