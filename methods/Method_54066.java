/** 
 * Unsafe version of  {@link #sentinel(byte) sentinel}. 
 */
public static void nsentinel(long struct,byte value){
  UNSAFE.putByte(null,struct + AIPropertyStore.SENTINEL,value);
}
