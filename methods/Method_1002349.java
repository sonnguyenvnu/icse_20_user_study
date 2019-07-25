public void delete(CleanupRecord record) throws RuntimeException {
  record.setEnabled(false);
  record.setRecordFlag(0x0);
  try {
    ByteBuffer buffer=ByteBuffer.allocate(1);
    int startIndex=record.getStartIndex();
    this.channel.position(startIndex);
    buffer.put((byte)0x0);
    buffer.flip();
    this.channel.write(buffer);
  }
 catch (  Exception ex) {
    throw new IllegalStateException(ex);
  }
  this.unRegisterRecord(record);
}
