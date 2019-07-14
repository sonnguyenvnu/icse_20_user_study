public void setButtons(MessageObject messageObject,boolean openKeyboard){
  if (replyingMessageObject != null && replyingMessageObject == botButtonsMessageObject && replyingMessageObject != messageObject) {
    botMessageObject=messageObject;
    return;
  }
  if (botButton == null || botButtonsMessageObject != null && botButtonsMessageObject == messageObject || botButtonsMessageObject == null && messageObject == null) {
    return;
  }
  if (botKeyboardView == null) {
    botKeyboardView=new BotKeyboardView(parentActivity);
    botKeyboardView.setVisibility(GONE);
    botKeyboardView.setDelegate(button -> {
      MessageObject object=replyingMessageObject != null ? replyingMessageObject : ((int)dialog_id < 0 ? botButtonsMessageObject : null);
      didPressedBotButton(button,object,replyingMessageObject != null ? replyingMessageObject : botButtonsMessageObject);
      if (replyingMessageObject != null) {
        openKeyboardInternal();
        setButtons(botMessageObject,false);
      }
 else       if (botButtonsMessageObject.messageOwner.reply_markup.single_use) {
        openKeyboardInternal();
        SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
        preferences.edit().putInt("answered_" + dialog_id,botButtonsMessageObject.getId()).commit();
      }
      if (delegate != null) {
        delegate.onMessageSend(null);
      }
    }
);
    sizeNotifierLayout.addView(botKeyboardView);
  }
  botButtonsMessageObject=messageObject;
  botReplyMarkup=messageObject != null && messageObject.messageOwner.reply_markup instanceof TLRPC.TL_replyKeyboardMarkup ? (TLRPC.TL_replyKeyboardMarkup)messageObject.messageOwner.reply_markup : null;
  botKeyboardView.setPanelHeight(AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? keyboardHeightLand : keyboardHeight);
  botKeyboardView.setButtons(botReplyMarkup);
  if (botReplyMarkup != null) {
    SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
    boolean keyboardHidden=preferences.getInt("hidekeyboard_" + dialog_id,0) == messageObject.getId();
    boolean showPopup=true;
    if (botButtonsMessageObject != replyingMessageObject && botReplyMarkup.single_use) {
      if (preferences.getInt("answered_" + dialog_id,0) == messageObject.getId()) {
        showPopup=false;
      }
    }
    if (showPopup && !keyboardHidden && messageEditText.length() == 0 && !isPopupShowing()) {
      showPopup(1,1);
    }
  }
 else {
    if (isPopupShowing() && currentPopupContentType == 1) {
      if (openKeyboard) {
        openKeyboardInternal();
      }
 else {
        showPopup(0,1);
      }
    }
  }
  updateBotButton();
}
