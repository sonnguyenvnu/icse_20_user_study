@Override public long size(){
  initBytes();
  return bytes.readRemaining();
}
