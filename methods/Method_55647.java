/** 
 * Sets all bytes in a specified block of memory to a fixed value (usually zero).
 * @param ptr   the starting memory address
 * @param value the value to set (memSet will convert it to unsigned byte)
 * @param < T >   the struct type
 */
public static <T extends Struct>void memSet(T ptr,int value){
  memSet(ptr.address,value,ptr.sizeof());
}
