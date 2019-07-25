public long size(){
  return Streams.stream(revokingDB.iterator()).count();
}
