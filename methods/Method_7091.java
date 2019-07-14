public boolean hasMessagesToReply(){
  for (int a=0; a < pushMessages.size(); a++) {
    MessageObject messageObject=pushMessages.get(a);
    long dialog_id=messageObject.getDialogId();
    if (messageObject.messageOwner.mentioned && messageObject.messageOwner.action instanceof TLRPC.TL_messageActionPinMessage || (int)dialog_id == 0 || messageObject.messageOwner.to_id.channel_id != 0 && !messageObject.isMegagroup()) {
      continue;
    }
    return true;
  }
  return false;
}
