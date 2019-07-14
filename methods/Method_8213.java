@Override public void onFragmentDestroy(){
  super.onFragmentDestroy();
  if (chatActivityEnterView != null) {
    chatActivityEnterView.onDestroy();
  }
  if (mentionsAdapter != null) {
    mentionsAdapter.onDestroy();
  }
  if (chatAttachAlert != null) {
    chatAttachAlert.dismissInternal();
  }
  if (undoView != null) {
    undoView.hide(true,0);
  }
  MessagesController.getInstance(currentAccount).setLastCreatedDialogId(dialog_id,false);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagesDidLoad);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.emojiDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didUpdateConnectionState);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.updateInterfaces);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didReceiveNewMessages);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.closeChats);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagesRead);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagesDeleted);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.historyCleared);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messageReceivedByServer);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messageReceivedByAck);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messageSendError);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.chatInfoDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.encryptedChatUpdated);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagesReadEncrypted);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.removeAllMessagesFromDialog);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.contactsDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingProgressDidChanged);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingDidReset);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.screenshotTook);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.blockedUsersDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.fileNewChunkAvailable);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didCreatedNewDeleteTask);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingDidStart);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingGoingToStop);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.updateMessageMedia);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.replaceMessagesObjects);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.notificationsSettingsUpdated);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.replyMessagesDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didReceivedWebpages);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didReceivedWebpagesInUpdates);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagesReadContent);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.botInfoDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.botKeyboardDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.chatSearchResultsAvailable);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.chatSearchResultsLoading);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.messagePlayingPlayStateChanged);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didUpdatedMessagesViews);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.chatInfoCantLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.pinnedMessageDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.peerSettingsDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.newDraftReceived);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.userInfoDidLoad);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.didSetNewWallpapper);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.channelRightsUpdated);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.updateMentionsCount);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.audioRecordTooShort);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didUpdatePollResults);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.chatOnlineCountDidLoad);
  NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.videoLoadingStateChanged);
  if (AndroidUtilities.isTablet()) {
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.openedChatChanged,dialog_id,true);
  }
  if (currentUser != null) {
    MediaController.getInstance().stopMediaObserver();
  }
  if (currentEncryptedChat != null) {
    try {
      if (Build.VERSION.SDK_INT >= 23 && (SharedConfig.passcodeHash.length() == 0 || SharedConfig.allowScreenCapture)) {
        MediaController.getInstance().setFlagSecure(this,false);
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
  if (currentUser != null) {
    MessagesController.getInstance(currentAccount).cancelLoadFullUser(currentUser.id);
  }
  AndroidUtilities.removeAdjustResize(getParentActivity(),classGuid);
  if (stickersAdapter != null) {
    stickersAdapter.onDestroy();
  }
  if (chatAttachAlert != null) {
    chatAttachAlert.onDestroy();
  }
  AndroidUtilities.unlockOrientation(getParentActivity());
  if (ChatObject.isChannel(currentChat)) {
    MessagesController.getInstance(currentAccount).startShortPoll(currentChat,true);
  }
}
