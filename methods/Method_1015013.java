@Override public boolean filter(Conversation conversation){
  if (conversation.type == Conversation.ConversationType.Single || conversation.type == Conversation.ConversationType.Group) {
    return false;
  }
  return true;
}
