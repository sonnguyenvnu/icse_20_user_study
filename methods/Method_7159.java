public int sendVote(final MessageObject messageObject,final TLRPC.TL_pollAnswer answer,final Runnable finishRunnable){
  if (messageObject == null) {
    return 0;
  }
  final String key="poll_" + messageObject.getPollId();
  if (waitingForCallback.containsKey(key)) {
    return 0;
  }
  waitingForVote.put(key,answer != null ? answer.option : new byte[0]);
  TLRPC.TL_messages_sendVote req=new TLRPC.TL_messages_sendVote();
  req.msg_id=messageObject.getId();
  req.peer=MessagesController.getInstance(currentAccount).getInputPeer((int)messageObject.getDialogId());
  if (answer != null) {
    req.options.add(answer.option);
  }
  return ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      voteSendTime.put(messageObject.getPollId(),0L);
      MessagesController.getInstance(currentAccount).processUpdates((TLRPC.Updates)response,false);
      voteSendTime.put(messageObject.getPollId(),SystemClock.uptimeMillis());
    }
    AndroidUtilities.runOnUIThread(new Runnable(){
      @Override public void run(){
        waitingForVote.remove(key);
        if (finishRunnable != null) {
          finishRunnable.run();
        }
      }
    }
);
  }
);
}
