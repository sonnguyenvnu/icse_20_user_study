public void getDialogFolderId(long dialogId,IntCallback callback){
  storageQueue.postRunnable(() -> {
    try {
      int folderId;
      SQLiteCursor cursor=database.queryFinalized("SELECT folder_id FROM dialogs WHERE did = ?",dialogId);
      if (cursor.next()) {
        folderId=cursor.intValue(0);
      }
 else {
        folderId=-1;
      }
      cursor.dispose();
      AndroidUtilities.runOnUIThread(() -> callback.run(folderId));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
}
