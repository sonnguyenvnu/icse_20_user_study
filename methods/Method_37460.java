@Override public int read(){
  return charBuffer.position() < charBuffer.limit() ? charBuffer.get() : -1;
}
