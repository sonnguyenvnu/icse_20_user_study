private void updateSecretStatus(){
  if (bottomOverlay == null) {
    return;
  }
  boolean hideKeyboard=false;
  if (currentChat != null && !ChatObject.canSendMessages(currentChat) && (!ChatObject.isChannel(currentChat) || currentChat.megagroup)) {
    if (currentChat.default_banned_rights != null && currentChat.default_banned_rights.send_messages) {
      bottomOverlayText.setText(LocaleController.getString("GlobalSendMessageRestricted",R.string.GlobalSendMessageRestricted));
    }
 else     if (AndroidUtilities.isBannedForever(currentChat.banned_rights)) {
      bottomOverlayText.setText(LocaleController.getString("SendMessageRestrictedForever",R.string.SendMessageRestrictedForever));
    }
 else {
      bottomOverlayText.setText(LocaleController.formatString("SendMessageRestricted",R.string.SendMessageRestricted,LocaleController.formatDateForBan(currentChat.banned_rights.until_date)));
    }
    bottomOverlay.setVisibility(View.VISIBLE);
    if (mentionListAnimation != null) {
      mentionListAnimation.cancel();
      mentionListAnimation=null;
    }
    mentionContainer.setVisibility(View.GONE);
    mentionContainer.setTag(null);
    updateMessageListAccessibilityVisibility();
    hideKeyboard=true;
    if (stickersAdapter != null) {
      stickersAdapter.hide();
    }
  }
 else {
    if (currentEncryptedChat == null || bigEmptyView == null) {
      bottomOverlay.setVisibility(View.INVISIBLE);
      if (stickersAdapter != null && chatActivityEnterView != null && chatActivityEnterView.hasText()) {
        stickersAdapter.loadStikersForEmoji(chatActivityEnterView.getFieldText(),false);
      }
      return;
    }
    if (currentEncryptedChat instanceof TLRPC.TL_encryptedChatRequested) {
      bottomOverlayText.setText(LocaleController.getString("EncryptionProcessing",R.string.EncryptionProcessing));
      bottomOverlay.setVisibility(View.VISIBLE);
      hideKeyboard=true;
    }
 else     if (currentEncryptedChat instanceof TLRPC.TL_encryptedChatWaiting) {
      bottomOverlayText.setText(AndroidUtilities.replaceTags(LocaleController.formatString("AwaitingEncryption",R.string.AwaitingEncryption,"<b>" + currentUser.first_name + "</b>")));
      bottomOverlay.setVisibility(View.VISIBLE);
      hideKeyboard=true;
    }
 else     if (currentEncryptedChat instanceof TLRPC.TL_encryptedChatDiscarded) {
      bottomOverlayText.setText(LocaleController.getString("EncryptionRejected",R.string.EncryptionRejected));
      bottomOverlay.setVisibility(View.VISIBLE);
      chatActivityEnterView.setFieldText("");
      DataQuery.getInstance(currentAccount).cleanDraft(dialog_id,false);
      hideKeyboard=true;
    }
 else     if (currentEncryptedChat instanceof TLRPC.TL_encryptedChat) {
      bottomOverlay.setVisibility(View.INVISIBLE);
    }
    checkRaiseSensors();
    checkActionBarMenu();
  }
  if (inPreviewMode) {
    bottomOverlay.setVisibility(View.INVISIBLE);
  }
  if (hideKeyboard) {
    chatActivityEnterView.hidePopup(false);
    if (getParentActivity() != null) {
      AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
    }
  }
}
