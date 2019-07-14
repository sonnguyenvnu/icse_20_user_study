private void deletePeer(final int did,final int type){
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    try {
      MessagesStorage.getInstance(currentAccount).getDatabase().executeFast(String.format(Locale.US,"DELETE FROM chat_hints WHERE did = %d AND type = %d",did,type)).stepThis().dispose();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
