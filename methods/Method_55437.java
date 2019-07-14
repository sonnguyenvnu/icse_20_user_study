/** 
 * Allocates a direct native-order intbuffer with the specified number of elements.
 * @param capacity The capacity, in ints
 * @return an IntBuffer
 */
public static IntBuffer createIntBuffer(int capacity){
  return createByteBuffer(getAllocationSize(capacity,2)).asIntBuffer();
}
