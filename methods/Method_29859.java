private RebroadcastBroadcastWriter findWriter(long broadcastId){
  for (  RebroadcastBroadcastWriter writer : getWriters()) {
    if (writer.getBroadcastId() == broadcastId) {
      return writer;
    }
  }
  return null;
}
