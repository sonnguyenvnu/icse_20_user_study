private void jumpToDate(int date){
  if (messages.isEmpty()) {
    return;
  }
  MessageObject firstMessage=messages.get(0);
  MessageObject lastMessage=messages.get(messages.size() - 1);
  if (firstMessage.messageOwner.date >= date && lastMessage.messageOwner.date <= date) {
    for (int a=messages.size() - 1; a >= 0; a--) {
      MessageObject message=messages.get(a);
      if (message.messageOwner.date >= date && message.getId() != 0) {
        scrollToMessageId(message.getId(),0,false,message.getDialogId() == mergeDialogId ? 1 : 0,false);
        break;
      }
    }
  }
 else   if ((int)dialog_id != 0) {
    clearChatData();
    waitingForLoad.add(lastLoadIndex);
    MessagesController.getInstance(currentAccount).loadMessages(dialog_id,30,0,date,true,0,classGuid,4,0,ChatObject.isChannel(currentChat),lastLoadIndex++);
    floatingDateView.setAlpha(0.0f);
    floatingDateView.setTag(null);
  }
}
