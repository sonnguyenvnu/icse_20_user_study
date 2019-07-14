public void loadChannelAdmins(final int chatId){
  storageQueue.postRunnable(() -> {
    try {
      SQLiteCursor cursor=database.queryFinalized("SELECT uid FROM channel_admins WHERE did = " + chatId);
      ArrayList<Integer> ids=new ArrayList<>();
      while (cursor.next()) {
        ids.add(cursor.intValue(0));
      }
      cursor.dispose();
      MessagesController.getInstance(currentAccount).processLoadedChannelAdmins(ids,chatId,true);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
