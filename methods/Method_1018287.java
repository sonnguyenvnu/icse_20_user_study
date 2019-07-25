public void handle() throws IOException {
  int count;
  ByteBuffer byteBuffer=ByteBuffer.allocate(Constants.BUFFER_CHUNK);
  while ((count=socketChannel.read(byteBuffer)) > 0) {
    byteBuffer.flip();
    for (int i=0; i < count; ++i) {
      byteScanner(byteBuffer.get());
    }
  }
}
