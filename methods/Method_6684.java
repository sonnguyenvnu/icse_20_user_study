public void generateWaveform(MessageObject messageObject){
  final String id=messageObject.getId() + "_" + messageObject.getDialogId();
  final String path=FileLoader.getPathToMessage(messageObject.messageOwner).getAbsolutePath();
  if (generatingWaveform.containsKey(id)) {
    return;
  }
  generatingWaveform.put(id,messageObject);
  Utilities.globalQueue.postRunnable(() -> {
    final byte[] waveform=getWaveform(path);
    AndroidUtilities.runOnUIThread(() -> {
      MessageObject messageObject1=generatingWaveform.remove(id);
      if (messageObject1 == null) {
        return;
      }
      if (waveform != null) {
        for (int a=0; a < messageObject1.getDocument().attributes.size(); a++) {
          TLRPC.DocumentAttribute attribute=messageObject1.getDocument().attributes.get(a);
          if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
            attribute.waveform=waveform;
            attribute.flags|=4;
            break;
          }
        }
        TLRPC.TL_messages_messages messagesRes=new TLRPC.TL_messages_messages();
        messagesRes.messages.add(messageObject1.messageOwner);
        MessagesStorage.getInstance(messageObject1.currentAccount).putMessages(messagesRes,messageObject1.getDialogId(),-1,0,false);
        ArrayList<MessageObject> arrayList=new ArrayList<>();
        arrayList.add(messageObject1);
        NotificationCenter.getInstance(messageObject1.currentAccount).postNotificationName(NotificationCenter.replaceMessagesObjects,messageObject1.getDialogId(),arrayList);
      }
    }
);
  }
);
}
