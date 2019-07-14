private void processRowSelect(View view,boolean outside,float touchX,float touchY){
  MessageObject message=null;
  if (view instanceof ChatMessageCell) {
    message=((ChatMessageCell)view).getMessageObject();
  }
 else   if (view instanceof ChatActionCell) {
    message=((ChatActionCell)view).getMessageObject();
  }
  int type=getMessageType(message);
  if (type < 2 || type == 20) {
    return;
  }
  addToSelectedMessages(message,outside);
  updateActionModeTitle();
  updateVisibleRows();
}
