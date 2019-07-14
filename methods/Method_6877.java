private void removeFolder(int folderId){
  long dialogId=DialogObject.makeFolderDialogId(folderId);
  TLRPC.Dialog dialog=dialogs_dict.get(dialogId);
  if (dialog == null) {
    return;
  }
  dialogs_dict.remove(dialogId);
  allDialogs.remove(dialog);
  sortDialogs(null);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.folderBecomeEmpty,folderId);
}
