private void updateBottomOverlay(){
  if (bottomOverlayChatText == null) {
    return;
  }
  if (currentChat != null) {
    if (ChatObject.isChannel(currentChat) && !(currentChat instanceof TLRPC.TL_channelForbidden)) {
      if (ChatObject.isNotInChat(currentChat)) {
        if (MessagesController.getInstance(currentAccount).isJoiningChannel(currentChat.id)) {
          showBottomOverlayProgress(true,false);
        }
 else {
          bottomOverlayChatText.setText(LocaleController.getString("ChannelJoin",R.string.ChannelJoin));
          showBottomOverlayProgress(false,false);
        }
      }
 else {
        if (!MessagesController.getInstance(currentAccount).isDialogMuted(dialog_id)) {
          bottomOverlayChatText.setText(LocaleController.getString("ChannelMute",R.string.ChannelMute));
        }
 else {
          bottomOverlayChatText.setText(LocaleController.getString("ChannelUnmute",R.string.ChannelUnmute));
        }
        showBottomOverlayProgress(false,bottomOverlayProgress.getTag() != null);
      }
      if (!ChatObject.isNotInChat(currentChat) && !currentChat.megagroup && (currentChat.has_link || chatInfo != null && chatInfo.linked_chat_id != 0)) {
        bottomOverlayChatText2.setText(LocaleController.getString("ChannelDiscuss",R.string.ChannelDiscuss));
        bottomOverlayChatText2.setVisibility(View.VISIBLE);
        bottomOverlayChatText2.updateCounter();
      }
 else {
        bottomOverlayChatText2.setVisibility(View.GONE);
      }
    }
 else {
      bottomOverlayChatText.setText(LocaleController.getString("DeleteThisGroup",R.string.DeleteThisGroup));
    }
  }
 else {
    showBottomOverlayProgress(false,false);
    if (userBlocked) {
      if (currentUser.bot) {
        bottomOverlayChatText.setText(LocaleController.getString("BotUnblock",R.string.BotUnblock));
      }
 else {
        bottomOverlayChatText.setText(LocaleController.getString("Unblock",R.string.Unblock));
      }
      if (botButtons != null) {
        botButtons=null;
        if (chatActivityEnterView != null) {
          if (replyingMessageObject != null && botReplyButtons == replyingMessageObject) {
            botReplyButtons=null;
            hideFieldPanel(false);
          }
          chatActivityEnterView.setButtons(botButtons,false);
        }
      }
    }
 else     if (botUser != null && currentUser.bot) {
      bottomOverlayChatText.setText(LocaleController.getString("BotStart",R.string.BotStart));
      chatActivityEnterView.hidePopup(false);
      if (getParentActivity() != null) {
        AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
      }
    }
 else {
      bottomOverlayChatText.setText(LocaleController.getString("DeleteThisChat",R.string.DeleteThisChat));
    }
  }
  if (inPreviewMode) {
    searchContainer.setVisibility(View.INVISIBLE);
    bottomOverlayChat.setVisibility(View.INVISIBLE);
    chatActivityEnterView.setFieldFocused(false);
    chatActivityEnterView.setVisibility(View.INVISIBLE);
  }
 else   if (searchItem != null && searchItem.getVisibility() == View.VISIBLE) {
    searchContainer.setVisibility(View.VISIBLE);
    bottomOverlayChat.setVisibility(View.INVISIBLE);
    chatActivityEnterView.setFieldFocused(false);
    chatActivityEnterView.setVisibility(View.INVISIBLE);
    if (chatActivityEnterView.isTopViewVisible()) {
      topViewWasVisible=1;
      chatActivityEnterView.hideTopView(false);
    }
 else {
      topViewWasVisible=2;
    }
  }
 else {
    searchContainer.setVisibility(View.INVISIBLE);
    if (muteItem != null) {
      if (currentChat != null && ChatObject.isNotInChat(currentChat)) {
        muteItem.setVisibility(View.GONE);
      }
 else {
        muteItem.setVisibility(View.VISIBLE);
      }
    }
    if (currentChat != null && (ChatObject.isNotInChat(currentChat) || !ChatObject.canWriteToChat(currentChat)) || currentUser != null && (UserObject.isDeleted(currentUser) || userBlocked)) {
      if (chatActivityEnterView.isEditingMessage()) {
        chatActivityEnterView.setVisibility(View.VISIBLE);
        bottomOverlayChat.setVisibility(View.INVISIBLE);
        chatActivityEnterView.setFieldFocused();
        AndroidUtilities.runOnUIThread(() -> chatActivityEnterView.openKeyboard(),100);
      }
 else {
        bottomOverlayChat.setVisibility(View.VISIBLE);
        chatActivityEnterView.setFieldFocused(false);
        chatActivityEnterView.setVisibility(View.INVISIBLE);
        chatActivityEnterView.closeKeyboard();
        if (stickersAdapter != null) {
          stickersAdapter.hide();
        }
      }
      attachItem.setVisibility(View.GONE);
      editTextItem.setVisibility(View.GONE);
      headerItem.setVisibility(View.VISIBLE);
    }
 else {
      if (botUser != null && currentUser.bot) {
        bottomOverlayChat.setVisibility(View.VISIBLE);
        chatActivityEnterView.setVisibility(View.INVISIBLE);
      }
 else {
        chatActivityEnterView.setVisibility(View.VISIBLE);
        bottomOverlayChat.setVisibility(View.INVISIBLE);
      }
    }
    if (topViewWasVisible == 1) {
      chatActivityEnterView.showTopView(false,false);
      topViewWasVisible=0;
    }
  }
  checkRaiseSensors();
}
