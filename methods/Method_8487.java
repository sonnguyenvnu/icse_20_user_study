private void updatePollMusicButton(){
  if (baseFragment instanceof ChatActivity) {
    if (attachButtons.isEmpty()) {
      return;
    }
    boolean allowPoll;
    if (editingMessageObject != null) {
      allowPoll=false;
    }
 else {
      TLRPC.Chat currentChat=((ChatActivity)baseFragment).getCurrentChat();
      allowPoll=currentChat != null && ChatObject.canSendPolls(currentChat);
    }
    String text=allowPoll ? LocaleController.getString("Poll",R.string.Poll) : LocaleController.getString("AttachMusic",R.string.AttachMusic);
    AttachButton attachButton=attachButtons.get(3);
    attachButton.setTag(allowPoll ? 9 : 3);
    attachButton.setTextAndIcon(text,Theme.chat_attachButtonDrawables[allowPoll ? 9 : 3]);
  }
}
