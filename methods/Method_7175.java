private DelayedMessage findMaxDelayedMessageForMessageId(int messageId,long dialogId){
  DelayedMessage maxDelayedMessage=null;
  int maxDalyedMessageId=Integer.MIN_VALUE;
  for (  HashMap.Entry<String,ArrayList<DelayedMessage>> entry : delayedMessages.entrySet()) {
    ArrayList<DelayedMessage> messages=entry.getValue();
    int size=messages.size();
    for (int a=0; a < size; a++) {
      DelayedMessage delayedMessage=messages.get(a);
      if ((delayedMessage.type == 4 || delayedMessage.type == 0) && delayedMessage.peer == dialogId) {
        int mid=0;
        if (delayedMessage.obj != null) {
          mid=delayedMessage.obj.getId();
        }
 else         if (delayedMessage.messageObjects != null && !delayedMessage.messageObjects.isEmpty()) {
          mid=delayedMessage.messageObjects.get(delayedMessage.messageObjects.size() - 1).getId();
        }
        if (mid != 0 && mid > messageId) {
          if (maxDelayedMessage == null && maxDalyedMessageId < mid) {
            maxDelayedMessage=delayedMessage;
            maxDalyedMessageId=mid;
          }
        }
      }
    }
  }
  return maxDelayedMessage;
}
