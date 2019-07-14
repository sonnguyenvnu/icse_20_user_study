private void removeMessageObject(MessageObject messageObject){
  int index=messages.indexOf(messageObject);
  if (index == -1) {
    return;
  }
  messages.remove(index);
  if (chatAdapter != null) {
    chatAdapter.notifyItemRemoved(chatAdapter.messagesStartRow + messages.size() - index - 1);
  }
}
