public void clearRecentSearch(){
  recentSearchObjectsById=new LongSparseArray<>();
  recentSearchObjects=new ArrayList<>();
  notifyDataSetChanged();
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      MessagesStorage.getInstance(currentAccount).getDatabase().executeFast("DELETE FROM search_recent WHERE 1").stepThis().dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
