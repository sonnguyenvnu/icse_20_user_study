@Override protected boolean isReusable(MemoryChunk value){
  Preconditions.checkNotNull(value);
  return !value.isClosed();
}
