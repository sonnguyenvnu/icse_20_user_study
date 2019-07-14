public void addDialogAction(long did,boolean clean){
  TLRPC.Dialog dialog=dialogs_dict.get(did);
  if (dialog == null) {
    return;
  }
  if (clean) {
    clearingHistoryDialogs.put(did,dialog);
  }
 else {
    deletingDialogs.put(did,dialog);
    allDialogs.remove(dialog);
    sortDialogs(null);
  }
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload,true);
}
