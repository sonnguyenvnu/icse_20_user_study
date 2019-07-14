/** 
 * Allocates a direct native-ordered bytebuffer with the specified capacity.
 * @param capacity The capacity, in bytes
 * @return a ByteBuffer
 */
public static ByteBuffer createByteBuffer(int capacity){
  return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
}
