private DeleteBroadcastWriter findWriter(long broadcastId){
  for (  DeleteBroadcastWriter writer : getWriters()) {
    if (writer.getBroadcastId() == broadcastId) {
      return writer;
    }
  }
  return null;
}
