public long timestamp(){
  return s.segmentBS.readLong(replicationBytesOffset);
}
