private void processDone(){
  if (!isPrivate && ((currentChat.username == null && usernameTextView.length() != 0) || (currentChat.username != null && !currentChat.username.equalsIgnoreCase(usernameTextView.getText().toString())))) {
    if (usernameTextView.length() != 0 && !lastNameAvailable) {
      Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
      if (v != null) {
        v.vibrate(200);
      }
      AndroidUtilities.shakeView(checkTextView,2,0);
      return;
    }
  }
  String oldUserName=currentChat.username != null ? currentChat.username : "";
  String newUserName=isPrivate ? "" : usernameTextView.getText().toString();
  if (!oldUserName.equals(newUserName)) {
    if (!ChatObject.isChannel(currentChat)) {
      MessagesController.getInstance(currentAccount).convertToMegaGroup(getParentActivity(),chatId,param -> {
        chatId=param;
        currentChat=MessagesController.getInstance(currentAccount).getChat(param);
        processDone();
      }
);
      return;
    }
 else {
      MessagesController.getInstance(currentAccount).updateChannelUserName(chatId,newUserName);
      currentChat.username=newUserName;
    }
  }
  finishFragment();
}
