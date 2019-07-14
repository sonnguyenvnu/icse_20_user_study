@Override public void onResume(){
  super.onResume();
  AndroidUtilities.requestAdjustResize(getParentActivity(),classGuid);
  MediaController.getInstance().startRaiseToEarSensors(this);
  checkRaiseSensors();
  if (chatAttachAlert != null) {
    chatAttachAlert.onResume();
  }
  if (contentView != null) {
    contentView.onResume();
  }
  if (firstOpen) {
    if (MessagesController.getInstance(currentAccount).isProxyDialog(dialog_id,true)) {
      SharedPreferences preferences=MessagesController.getGlobalNotificationsSettings();
      if (preferences.getLong("proxychannel",0) != dialog_id) {
        preferences.edit().putLong("proxychannel",dialog_id).commit();
        AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setMessage(LocaleController.getString("UseProxySponsorInfo",R.string.UseProxySponsorInfo));
        builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
        showDialog(builder.create());
      }
    }
  }
  checkActionBarMenu();
  if (replyImageLocation != null && replyImageView != null) {
    replyImageView.setImage(ImageLocation.getForObject(replyImageLocation,replyImageLocationObject),"50_50",ImageLocation.getForObject(replyImageThumbLocation,replyImageLocationObject),"50_50_b",null,replyImageSize,replyImageCacheType,replyingMessageObject);
  }
  if (pinnedImageLocation != null && pinnedMessageImageView != null) {
    pinnedMessageImageView.setImage(ImageLocation.getForObject(pinnedImageLocation,pinnedImageLocationObject),"50_50",ImageLocation.getForObject(pinnedImageThumbLocation,pinnedImageLocationObject),"50_50_b",null,pinnedImageSize,pinnedImageCacheType,pinnedMessageObject);
  }
  NotificationsController.getInstance(currentAccount).setOpenedDialogId(dialog_id);
  MessagesController.getInstance(currentAccount).setLastVisibleDialogId(dialog_id,true);
  if (scrollToTopOnResume) {
    if (scrollToTopUnReadOnResume && scrollToMessage != null) {
      if (chatListView != null) {
        int yOffset;
        boolean bottom=true;
        if (scrollToMessagePosition == -9000) {
          yOffset=getScrollOffsetForMessage(scrollToMessage);
          bottom=false;
        }
 else         if (scrollToMessagePosition == -10000) {
          yOffset=-AndroidUtilities.dp(11);
          bottom=false;
        }
 else {
          yOffset=scrollToMessagePosition;
        }
        chatLayoutManager.scrollToPositionWithOffset(chatAdapter.messagesStartRow + messages.indexOf(scrollToMessage),yOffset,bottom);
      }
    }
 else {
      moveScrollToLastMessage();
    }
    scrollToTopUnReadOnResume=false;
    scrollToTopOnResume=false;
    scrollToMessage=null;
  }
  paused=false;
  pausedOnLastMessage=false;
  checkScrollForLoad(false);
  if (wasPaused) {
    wasPaused=false;
    if (chatAdapter != null) {
      chatAdapter.notifyDataSetChanged();
    }
  }
  fixLayout();
  applyDraftMaybe(false);
  if (bottomOverlayChat != null && bottomOverlayChat.getVisibility() != View.VISIBLE && !actionBar.isSearchFieldVisible()) {
    chatActivityEnterView.setFieldFocused(true);
  }
  if (chatActivityEnterView != null) {
    chatActivityEnterView.onResume();
  }
  if (currentUser != null) {
    chatEnterTime=System.currentTimeMillis();
    chatLeaveTime=0;
  }
  if (startVideoEdit != null) {
    AndroidUtilities.runOnUIThread(() -> {
      openVideoEditor(startVideoEdit,null);
      startVideoEdit=null;
    }
);
  }
  if (chatListView != null && (chatActivityEnterView == null || !chatActivityEnterView.isEditingMessage())) {
    chatListView.setOnItemLongClickListener(onItemLongClickListener);
    chatListView.setOnItemClickListener(onItemClickListener);
    chatListView.setLongClickable(true);
  }
  checkBotCommands();
  updateTitle();
}
