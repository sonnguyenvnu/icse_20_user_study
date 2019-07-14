private void didPressedButton(){
  if (buttonState == 0) {
    boolean result=MediaController.getInstance().playMessage(currentMessageObject);
    if (!currentMessageObject.isOut() && currentMessageObject.isContentUnread()) {
      if (currentMessageObject.messageOwner.to_id.channel_id == 0) {
        MessagesController.getInstance(currentAccount).markMessageContentAsRead(currentMessageObject);
        currentMessageObject.setContentIsRead();
      }
    }
    if (result) {
      buttonState=1;
      invalidate();
    }
  }
 else   if (buttonState == 1) {
    boolean result=MediaController.getInstance().pauseMessage(currentMessageObject);
    if (result) {
      buttonState=0;
      invalidate();
    }
  }
 else   if (buttonState == 2) {
    FileLoader.getInstance(currentAccount).loadFile(currentMessageObject.getDocument(),currentMessageObject,1,0);
    buttonState=4;
    invalidate();
  }
 else   if (buttonState == 3) {
    FileLoader.getInstance(currentAccount).cancelLoadFile(currentMessageObject.getDocument());
    buttonState=2;
    invalidate();
  }
}
