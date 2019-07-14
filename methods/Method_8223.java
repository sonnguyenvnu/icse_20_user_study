public void setBotUser(String value){
  if (inlineReturn != 0) {
    MessagesController.getInstance(currentAccount).sendBotStart(currentUser,value);
  }
 else {
    botUser=value;
    updateBottomOverlay();
  }
}
