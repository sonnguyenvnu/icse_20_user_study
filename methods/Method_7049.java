private void checkIfFolderEmptyInternal(int folderId){
  try {
    SQLiteCursor cursor=database.queryFinalized("SELECT did FROM dialogs WHERE folder_id = ?",folderId);
    if (!cursor.next()) {
      AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).onFolderEmpty(folderId));
      database.executeFast("DELETE FROM dialogs WHERE did = " + DialogObject.makeFolderDialogId(folderId)).stepThis().dispose();
    }
    cursor.dispose();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
