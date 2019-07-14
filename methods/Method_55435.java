/** 
 * Allocates a direct native-order shortbuffer with the specified number of elements.
 * @param capacity The capacity, in shorts
 * @return a ShortBuffer
 */
public static ShortBuffer createShortBuffer(int capacity){
  return createByteBuffer(getAllocationSize(capacity,1)).asShortBuffer();
}
