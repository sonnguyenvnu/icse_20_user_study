private void removeUser(int userId){
  if (!ChatObject.isChannel(currentChat)) {
    return;
  }
  TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(userId);
  MessagesController.getInstance(currentAccount).deleteUserFromChat(chatId,user,null);
  finishFragment();
}
