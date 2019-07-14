public MessagesController getMessagesController(){
  return MessagesController.getInstance(currentAccount);
}
