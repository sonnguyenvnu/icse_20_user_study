private MessageObject.GroupedMessages getValidGroupedMessage(MessageObject message){
  MessageObject.GroupedMessages groupedMessages=null;
  if (message.getGroupId() != 0) {
    groupedMessages=groupedMessagesMap.get(message.getGroupId());
    if (groupedMessages != null && (groupedMessages.messages.size() <= 1 || groupedMessages.positions.get(message) == null)) {
      groupedMessages=null;
    }
  }
  return groupedMessages;
}
