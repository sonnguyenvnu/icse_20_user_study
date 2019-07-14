/** 
 * Fills  {@link #paddingBuffer} using data from {@code input}, plus any additional buffered data at the end of  {@code buffer} (up to its {@code size}) required to fill it, advancing the input position.
 */
private void updatePaddingBuffer(ByteBuffer input,byte[] buffer,int size){
  int fromInputSize=Math.min(input.remaining(),paddingSize);
  int fromBufferSize=paddingSize - fromInputSize;
  System.arraycopy(buffer,size - fromBufferSize,paddingBuffer,0,fromBufferSize);
  input.position(input.limit() - fromInputSize);
  input.get(paddingBuffer,fromBufferSize,fromInputSize);
}
