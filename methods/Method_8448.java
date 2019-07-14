public void setDialogId(long id,int account){
  dialog_id=id;
  if (currentAccount != account) {
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.recordStarted);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.recordStartError);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.recordStopped);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.recordProgressChanged);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.closeChats);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.audioDidSent);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.audioRouteChanged);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingDidReset);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingProgressDidChanged);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.featuredStickersDidLoad);
    currentAccount=account;
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.recordStarted);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.recordStartError);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.recordStopped);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.recordProgressChanged);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.closeChats);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.audioDidSent);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.audioRouteChanged);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingDidReset);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingProgressDidChanged);
    NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.featuredStickersDidLoad);
  }
  int lower_id=(int)dialog_id;
  int high_id=(int)(dialog_id >> 32);
  if ((int)dialog_id < 0) {
    TLRPC.Chat currentChat=MessagesController.getInstance(currentAccount).getChat(-(int)dialog_id);
    silent=MessagesController.getNotificationsSettings(currentAccount).getBoolean("silent_" + dialog_id,false);
    canWriteToChannel=ChatObject.isChannel(currentChat) && (currentChat.creator || currentChat.admin_rights != null && currentChat.admin_rights.post_messages) && !currentChat.megagroup;
    if (notifyButton != null) {
      notifyButton.setVisibility(canWriteToChannel ? VISIBLE : GONE);
      notifyButton.setImageResource(silent ? R.drawable.input_notify_off : R.drawable.input_notify_on);
      attachLayout.setPivotX(AndroidUtilities.dp((botButton == null || botButton.getVisibility() == GONE) && (notifyButton == null || notifyButton.getVisibility() == GONE) ? 48 : 96));
    }
    if (attachLayout != null) {
      updateFieldRight(attachLayout.getVisibility() == VISIBLE ? 1 : 0);
    }
  }
  checkRoundVideo();
  updateFieldHint();
}
