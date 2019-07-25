@Override public void write(int b) throws IOException {
  byte[] tail=_buffers.peekLast();
  if (tail == null || _tailOffset == _bufferSize) {
    tail=new byte[_bufferSize];
    _tailOffset=0;
    _buffers.addLast(tail);
  }
  tail[_tailOffset++]=(byte)b;
}
