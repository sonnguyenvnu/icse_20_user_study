public void clearAllDrafts(){
  drafts.clear();
  draftMessages.clear();
  preferences.edit().clear().commit();
  MessagesController.getInstance(currentAccount).sortDialogs(null);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
}
