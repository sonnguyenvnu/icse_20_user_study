public void removeDialogAction(long did,boolean clean,boolean apply){
  TLRPC.Dialog dialog=dialogs_dict.get(did);
  if (dialog == null) {
    return;
  }
  if (clean) {
    clearingHistoryDialogs.remove(did);
  }
 else {
    deletingDialogs.remove(did);
    if (!apply) {
      allDialogs.add(dialog);
      sortDialogs(null);
    }
  }
  if (!apply) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload,true);
  }
}
