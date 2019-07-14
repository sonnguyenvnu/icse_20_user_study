private void updateInterfaceForCurrentMessage(int move){
  if (actionBar == null) {
    return;
  }
  if (lastResumedAccount != currentMessageObject.currentAccount) {
    if (lastResumedAccount >= 0) {
      ConnectionsManager.getInstance(lastResumedAccount).setAppPaused(true,false);
    }
    lastResumedAccount=currentMessageObject.currentAccount;
    ConnectionsManager.getInstance(lastResumedAccount).setAppPaused(false,false);
  }
  currentChat=null;
  currentUser=null;
  long dialog_id=currentMessageObject.getDialogId();
  chatActivityEnterView.setDialogId(dialog_id,currentMessageObject.currentAccount);
  if ((int)dialog_id != 0) {
    int lower_id=(int)dialog_id;
    if (lower_id > 0) {
      currentUser=MessagesController.getInstance(currentMessageObject.currentAccount).getUser(lower_id);
    }
 else {
      currentChat=MessagesController.getInstance(currentMessageObject.currentAccount).getChat(-lower_id);
      currentUser=MessagesController.getInstance(currentMessageObject.currentAccount).getUser(currentMessageObject.messageOwner.from_id);
    }
  }
 else {
    TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentMessageObject.currentAccount).getEncryptedChat((int)(dialog_id >> 32));
    currentUser=MessagesController.getInstance(currentMessageObject.currentAccount).getUser(encryptedChat.user_id);
  }
  if (currentChat != null && currentUser != null) {
    nameTextView.setText(currentChat.title);
    onlineTextView.setText(UserObject.getUserName(currentUser));
    nameTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
    nameTextView.setCompoundDrawablePadding(0);
  }
 else   if (currentUser != null) {
    nameTextView.setText(UserObject.getUserName(currentUser));
    if ((int)dialog_id == 0) {
      nameTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_white,0,0,0);
      nameTextView.setCompoundDrawablePadding(AndroidUtilities.dp(4));
    }
 else {
      nameTextView.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
      nameTextView.setCompoundDrawablePadding(0);
    }
  }
  prepareLayouts(move);
  updateSubtitle();
  checkAndUpdateAvatar();
  applyViewsLayoutParams(0);
}
