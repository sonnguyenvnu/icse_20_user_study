private LikeBroadcastWriter findWriter(long broadcastId){
  for (  LikeBroadcastWriter writer : getWriters()) {
    if (writer.getBroadcastId() == broadcastId) {
      return writer;
    }
  }
  return null;
}
