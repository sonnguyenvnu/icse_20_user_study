public void setInfo(TLRPC.ChatFull chatFull){
  info=chatFull;
  if (chatFull != null) {
    if (currentChat == null) {
      currentChat=MessagesController.getInstance(currentAccount).getChat(chatId);
    }
    historyHidden=!ChatObject.isChannel(currentChat) || info.hidden_prehistory;
  }
}
