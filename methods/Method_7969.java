public void forceResetMessageObject(){
  MessageObject messageObject=messageObjectToSet != null ? messageObjectToSet : currentMessageObject;
  currentMessageObject=null;
  setMessageObject(messageObject,currentMessagesGroup,pinnedBottom,pinnedTop);
}
