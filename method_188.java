long _XXXXX_(long ledgerId,ByteBuf entry) throws IOException {
synchronized (compactionLogLock) {
    int entrySize=entry.readableBytes() + 4;
    if (compactionLogChannel == null) {
      createNewCompactionLog();
    }
    ByteBuf sizeBuffer=this.sizeBuffer.get();
    sizeBuffer.clear();
    sizeBuffer.writeInt(entry.readableBytes());
    compactionLogChannel.write(sizeBuffer);
    long pos=compactionLogChannel.position();
    compactionLogChannel.write(entry);
    compactionLogChannel.registerWrittenEntry(ledgerId,entrySize);
    return (compactionLogChannel.getLogId() << 32L) | pos;
  }
}