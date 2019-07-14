public void forceResetDialogs(){
  resetDialogs(true,MessagesStorage.getInstance(currentAccount).getLastSeqValue(),MessagesStorage.getInstance(currentAccount).getLastPtsValue(),MessagesStorage.getInstance(currentAccount).getLastDateValue(),MessagesStorage.getInstance(currentAccount).getLastQtsValue());
  NotificationsController.getInstance(currentAccount).deleteAllNotificationChannels();
}
