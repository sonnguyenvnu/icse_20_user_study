private boolean fetch() throws IOException {
  if (byteBuffer != null && byteBuffer.remaining() == 0) {
    fillBuffer();
  }
  return byteBuffer != null;
}
