/** 
 * Creates a new direct CharBuffer that starts at the specified memory address and has the specified capacity. <p>The  {@code address} specified must be aligned to 2 bytes. If not, use {@code memByteBuffer(address, capacity * 2).asCharBuffer()}.</p>
 * @param address  the starting memory address
 * @param capacity the buffer capacity
 * @return the new CharBuffer
 */
public static CharBuffer memCharBuffer(long address,int capacity){
  if (CHECKS) {
    check(address);
  }
  return wrap(BUFFER_CHAR,address,capacity);
}
