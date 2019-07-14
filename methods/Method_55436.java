/** 
 * Allocates a direct native-order charbuffer with the specified number of elements.
 * @param capacity The capacity, in chars
 * @return an CharBuffer
 */
public static CharBuffer createCharBuffer(int capacity){
  return createByteBuffer(getAllocationSize(capacity,1)).asCharBuffer();
}
