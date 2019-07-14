public boolean processSendingText(CharSequence text){
  text=AndroidUtilities.getTrimmedString(text);
  int maxLength=MessagesController.getInstance(currentAccount).maxMessageLength;
  if (text.length() != 0) {
    int count=(int)Math.ceil(text.length() / (float)maxLength);
    for (int a=0; a < count; a++) {
      CharSequence[] message=new CharSequence[]{text.subSequence(a * maxLength,Math.min((a + 1) * maxLength,text.length()))};
      ArrayList<TLRPC.MessageEntity> entities=DataQuery.getInstance(currentAccount).getEntities(message);
      SendMessagesHelper.getInstance(currentAccount).sendMessage(message[0].toString(),dialog_id,replyingMessageObject,messageWebPage,messageWebPageSearch,entities,null,null);
    }
    return true;
  }
  return false;
}
