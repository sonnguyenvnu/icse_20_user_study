/** 
 * Allocates a PointerBuffer with the specified number of elements.
 * @param capacity The capacity, in memory addresses
 * @return a PointerBuffer
 */
public static PointerBuffer createPointerBuffer(int capacity){
  return PointerBuffer.allocateDirect(capacity);
}
