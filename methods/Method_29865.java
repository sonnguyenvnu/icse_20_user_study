private SendBroadcastCommentWriter findWriter(long broadcastId){
  for (  SendBroadcastCommentWriter writer : getWriters()) {
    if (writer.getBroadcastId() == broadcastId) {
      return writer;
    }
  }
  return null;
}
