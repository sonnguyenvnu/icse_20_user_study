/** 
 * Prepares to output  {@code size} bytes in {@code buffer}. 
 */
private void prepareForOutput(int size){
  if (buffer.capacity() < size) {
    buffer=ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
  }
 else {
    buffer.clear();
  }
  if (size > 0) {
    hasOutputNoise=true;
  }
}
