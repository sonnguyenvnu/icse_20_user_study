private void erase() throws IOException {
  for (int i=0; i < arkCommandBuffer.getPos(); ++i) {
    socketChannel.write(wrapSingleByte(BS));
  }
  for (int i=0; i < arkCommandBuffer.getSize(); ++i) {
    socketChannel.write(wrapSingleByte(SPACE));
  }
  for (int i=0; i < arkCommandBuffer.getSize(); ++i) {
    socketChannel.write(wrapSingleByte(BS));
  }
}
