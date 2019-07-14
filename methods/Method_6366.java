public boolean isMessageFound(final int messageId,boolean mergeDialog){
  return searchResultMessagesMap[mergeDialog ? 1 : 0].indexOfKey(messageId) >= 0;
}
