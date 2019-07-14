public void setDialogsInTransaction(boolean transaction){
  dialogsInTransaction=transaction;
  if (!transaction) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload,true);
  }
}
