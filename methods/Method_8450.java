private void updateFieldHint(){
  boolean isChannel=false;
  if ((int)dialog_id < 0) {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(-(int)dialog_id);
    isChannel=ChatObject.isChannel(chat) && !chat.megagroup;
  }
  if (editingMessageObject != null) {
    messageEditText.setHintText(editingCaption ? LocaleController.getString("Caption",R.string.Caption) : LocaleController.getString("TypeMessage",R.string.TypeMessage));
  }
 else   if (isChannel) {
    if (silent) {
      messageEditText.setHintText(LocaleController.getString("ChannelSilentBroadcast",R.string.ChannelSilentBroadcast));
    }
 else {
      messageEditText.setHintText(LocaleController.getString("ChannelBroadcast",R.string.ChannelBroadcast));
    }
  }
 else {
    messageEditText.setHintText(LocaleController.getString("TypeMessage",R.string.TypeMessage));
  }
}
