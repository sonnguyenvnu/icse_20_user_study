public void close(){
  getClient().close();
  closeAllChat();
  left.onLoadContacts(false);
}
