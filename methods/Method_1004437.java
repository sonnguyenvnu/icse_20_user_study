@Override public void process(Datagram syncData){
  ByteBuf body=syncData.getBody();
  int size=body.readInt();
  if (size == 0) {
    LOGGER.debug("sync dispatch log data empty");
    return;
  }
  long startOffset=body.readLong();
  long baseOffset=body.readLong();
  appendLogs(startOffset,baseOffset,body);
}
