public boolean loadRecentHashtags(){
  if (hashtagsLoadedFromDb) {
    return true;
  }
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized("SELECT id, date FROM hashtag_recent_v2 WHERE 1");
      final ArrayList<HashtagObject> arrayList=new ArrayList<>();
      final HashMap<String,HashtagObject> hashMap=new HashMap<>();
      while (cursor.next()) {
        HashtagObject hashtagObject=new HashtagObject();
        hashtagObject.hashtag=cursor.stringValue(0);
        hashtagObject.date=cursor.intValue(1);
        arrayList.add(hashtagObject);
        hashMap.put(hashtagObject.hashtag,hashtagObject);
      }
      cursor.dispose();
      Collections.sort(arrayList,(lhs,rhs) -> {
        if (lhs.date < rhs.date) {
          return 1;
        }
 else         if (lhs.date > rhs.date) {
          return -1;
        }
 else {
          return 0;
        }
      }
);
      AndroidUtilities.runOnUIThread(() -> setHashtags(arrayList,hashMap));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
  return false;
}
