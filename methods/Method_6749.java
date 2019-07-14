public boolean isFromChat(){
  if (getDialogId() == UserConfig.getInstance(currentAccount).clientUserId) {
    return true;
  }
  if (isMegagroup() || messageOwner.to_id != null && messageOwner.to_id.chat_id != 0) {
    return true;
  }
  if (messageOwner.to_id != null && messageOwner.to_id.channel_id != 0) {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(messageOwner.to_id.channel_id);
    return chat != null && chat.megagroup;
  }
  return false;
}
