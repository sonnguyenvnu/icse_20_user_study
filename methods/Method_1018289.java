private void render() throws IOException {
  socketChannel.write(ByteBuffer.wrap(arkCommandBuffer.getBuffer()));
  for (int i=0; i < arkCommandBuffer.getGap(); ++i) {
    socketChannel.write(ByteBuffer.wrap(new byte[]{BS}));
  }
}
