private ArrayList<MessageObject> createVoiceMessagesPlaylist(MessageObject startMessageObject,boolean playingUnreadMedia){
  ArrayList<MessageObject> messageObjects=new ArrayList<>();
  messageObjects.add(startMessageObject);
  int messageId=startMessageObject.getId();
  long startDialogId=startMessageObject.getDialogId();
  if (messageId != 0) {
    boolean started=false;
    for (int a=messages.size() - 1; a >= 0; a--) {
      MessageObject messageObject=messages.get(a);
      if (messageObject.getDialogId() == mergeDialogId && startMessageObject.getDialogId() != mergeDialogId) {
        continue;
      }
      if ((currentEncryptedChat == null && messageObject.getId() > messageId || currentEncryptedChat != null && messageObject.getId() < messageId) && (messageObject.isVoice() || messageObject.isRoundVideo()) && (!playingUnreadMedia || messageObject.isContentUnread() && !messageObject.isOut())) {
        messageObjects.add(messageObject);
      }
    }
  }
  return messageObjects;
}
