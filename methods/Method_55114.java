/** 
 * Unsafe version of  {@link #bit_gravity(int) bit_gravity}. 
 */
public static void nbit_gravity(long struct,int value){
  UNSAFE.putInt(null,struct + XSetWindowAttributes.BIT_GRAVITY,value);
}
