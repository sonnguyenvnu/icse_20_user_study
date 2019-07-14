@Override public void queueInput(ByteBuffer buffer){
  int remaining=buffer.remaining();
  if (remaining == 0) {
    return;
  }
  audioBufferSink.handleBuffer(buffer.asReadOnlyBuffer());
  if (this.buffer.capacity() < remaining) {
    this.buffer=ByteBuffer.allocateDirect(remaining).order(ByteOrder.nativeOrder());
  }
 else {
    this.buffer.clear();
  }
  this.buffer.put(buffer);
  this.buffer.flip();
  outputBuffer=this.buffer;
}
