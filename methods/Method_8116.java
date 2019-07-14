public MessageObject getMessageObject(int a){
  if (a >= itemsCount) {
    return null;
  }
  return messageObjects[a];
}
