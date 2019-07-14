/** 
 * Unsafe version of  {@link #bit_gravity}. 
 */
public static int nbit_gravity(long struct){
  return UNSAFE.getInt(null,struct + XSetWindowAttributes.BIT_GRAVITY);
}
