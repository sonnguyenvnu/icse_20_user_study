/** 
 * Sets all bytes in a specified block of memory to a fixed value (usually zero).
 * @param ptr   the starting memory address
 * @param value the value to set (memSet will convert it to unsigned byte)
 * @param < T >   the buffer type
 */
public static <T extends CustomBuffer<T>>void memSet(T ptr,int value){
  memSet(memAddress(ptr),value,Integer.toUnsignedLong(ptr.remaining()) * ptr.sizeof());
}
