private void checkBotKeyboard(){
  if (chatActivityEnterView == null || botButtons == null || userBlocked) {
    return;
  }
  if (botButtons.messageOwner.reply_markup instanceof TLRPC.TL_replyKeyboardForceReply) {
    SharedPreferences preferences=MessagesController.getMainSettings(currentAccount);
    if (preferences.getInt("answered_" + dialog_id,0) != botButtons.getId() && (replyingMessageObject == null || chatActivityEnterView.getFieldText() == null)) {
      botReplyButtons=botButtons;
      chatActivityEnterView.setButtons(botButtons);
      showFieldPanelForReply(botButtons);
    }
  }
 else {
    if (replyingMessageObject != null && botReplyButtons == replyingMessageObject) {
      botReplyButtons=null;
      hideFieldPanel(true);
    }
    chatActivityEnterView.setButtons(botButtons);
  }
}
