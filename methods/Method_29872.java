private SendBroadcastWriter findWriter(long writerId){
  for (  SendBroadcastWriter writer : getWriters()) {
    if (writer.getId() == writerId) {
      return writer;
    }
  }
  return null;
}
