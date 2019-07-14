@SuppressWarnings("ReferenceEquality") @Override public ByteBuffer getOutput(){
  ByteBuffer outputBuffer=this.outputBuffer;
  if (inputEnded && endBufferSize > 0 && outputBuffer == EMPTY_BUFFER) {
    if (buffer.capacity() < endBufferSize) {
      buffer=ByteBuffer.allocateDirect(endBufferSize).order(ByteOrder.nativeOrder());
    }
 else {
      buffer.clear();
    }
    buffer.put(endBuffer,0,endBufferSize);
    endBufferSize=0;
    buffer.flip();
    outputBuffer=buffer;
  }
  this.outputBuffer=EMPTY_BUFFER;
  return outputBuffer;
}
