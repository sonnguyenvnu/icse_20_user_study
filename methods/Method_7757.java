public void putRecentSearch(final long did,TLObject object){
  RecentSearchObject recentSearchObject=recentSearchObjectsById.get(did);
  if (recentSearchObject == null) {
    recentSearchObject=new RecentSearchObject();
    recentSearchObjectsById.put(did,recentSearchObject);
  }
 else {
    recentSearchObjects.remove(recentSearchObject);
  }
  recentSearchObjects.add(0,recentSearchObject);
  recentSearchObject.did=did;
  recentSearchObject.object=object;
  recentSearchObject.date=(int)(System.currentTimeMillis() / 1000);
  notifyDataSetChanged();
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      SQLitePreparedStatement state=MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("REPLACE INTO search_recent VALUES(?, ?)");
      state.requery();
      state.bindLong(1,did);
      state.bindInteger(2,(int)(System.currentTimeMillis() / 1000));
      state.step();
      state.dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
