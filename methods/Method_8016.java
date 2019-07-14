public void setDialog(long dialog_id,MessageObject messageObject,int date){
  currentDialogId=dialog_id;
  message=messageObject;
  isDialogCell=false;
  lastMessageDate=date;
  currentEditDate=messageObject != null ? messageObject.messageOwner.edit_date : 0;
  unreadCount=0;
  markUnread=false;
  messageId=messageObject != null ? messageObject.getId() : 0;
  mentionCount=0;
  lastUnreadState=messageObject != null && messageObject.isUnread();
  if (message != null) {
    lastSendState=message.messageOwner.send_state;
  }
  update(0);
}
