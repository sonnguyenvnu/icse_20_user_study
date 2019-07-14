@Override public boolean hasMore(){
  return offset < bufferSize || fileChannel != null;
}
