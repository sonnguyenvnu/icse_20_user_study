private void setCellSelectionBackground(MessageObject message,ChatMessageCell messageCell,int idx,boolean animated){
  MessageObject.GroupedMessages groupedMessages=getValidGroupedMessage(message);
  if (groupedMessages != null) {
    boolean hasUnselected=false;
    for (int a=0; a < groupedMessages.messages.size(); a++) {
      if (selectedMessagesIds[idx].indexOfKey(groupedMessages.messages.get(a).getId()) < 0) {
        hasUnselected=true;
        break;
      }
    }
    if (!hasUnselected) {
      groupedMessages=null;
    }
  }
  messageCell.setDrawSelectionBackground(groupedMessages == null);
  messageCell.setChecked(true,groupedMessages == null,animated);
}
