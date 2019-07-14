public void setCommand(MessageObject messageObject,String command,boolean longPress,boolean username){
  if (command == null || getVisibility() != VISIBLE) {
    return;
  }
  if (longPress) {
    String text=messageEditText.getText().toString();
    TLRPC.User user=messageObject != null && (int)dialog_id < 0 ? MessagesController.getInstance(currentAccount).getUser(messageObject.messageOwner.from_id) : null;
    if ((botCount != 1 || username) && user != null && user.bot && !command.contains("@")) {
      text=String.format(Locale.US,"%s@%s",command,user.username) + " " + text.replaceFirst("^/[a-zA-Z@\\d_]{1,255}(\\s|$)","");
    }
 else {
      text=command + " " + text.replaceFirst("^/[a-zA-Z@\\d_]{1,255}(\\s|$)","");
    }
    ignoreTextChange=true;
    messageEditText.setText(text);
    messageEditText.setSelection(messageEditText.getText().length());
    ignoreTextChange=false;
    if (delegate != null) {
      delegate.onTextChanged(messageEditText.getText(),true);
    }
    if (!keyboardVisible && currentPopupContentType == -1) {
      openKeyboard();
    }
  }
 else {
    TLRPC.User user=messageObject != null && (int)dialog_id < 0 ? MessagesController.getInstance(currentAccount).getUser(messageObject.messageOwner.from_id) : null;
    if ((botCount != 1 || username) && user != null && user.bot && !command.contains("@")) {
      SendMessagesHelper.getInstance(currentAccount).sendMessage(String.format(Locale.US,"%s@%s",command,user.username),dialog_id,replyingMessageObject,null,false,null,null,null);
    }
 else {
      SendMessagesHelper.getInstance(currentAccount).sendMessage(command,dialog_id,replyingMessageObject,null,false,null,null,null);
    }
  }
}
