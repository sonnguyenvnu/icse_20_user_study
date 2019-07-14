private void checkEditTimer(){
  if (chatActivityEnterView == null) {
    return;
  }
  MessageObject messageObject=chatActivityEnterView.getEditingMessageObject();
  if (messageObject == null) {
    return;
  }
  if (currentUser != null && currentUser.self) {
    return;
  }
  int dt=messageObject.canEditMessageAnytime(currentChat) ? 6 * 60 : MessagesController.getInstance(currentAccount).maxEditTime + 5 * 60 - Math.abs(ConnectionsManager.getInstance(currentAccount).getCurrentTime() - messageObject.messageOwner.date);
  if (dt > 0) {
    if (dt <= 5 * 60) {
      replyObjectTextView.setText(LocaleController.formatString("TimeToEdit",R.string.TimeToEdit,String.format("%d:%02d",dt / 60,dt % 60)));
    }
    AndroidUtilities.runOnUIThread(this::checkEditTimer,1000);
  }
 else {
    chatActivityEnterView.onEditTimeExpired();
    replyObjectTextView.setText(LocaleController.formatString("TimeToEditExpired",R.string.TimeToEditExpired));
  }
}
