/** 
 * Companion to  {@link #getStartKey(int)}. See its javadoc for details.
 */
private byte[] getEndKey(int regionCount){
  ByteBuffer regionWidth=ByteBuffer.allocate(4);
  regionWidth.putInt((int)(((1L << 32) - 1L) / regionCount * (regionCount - 1))).flip();
  return StaticArrayBuffer.of(regionWidth).getBytes(0,4);
}
