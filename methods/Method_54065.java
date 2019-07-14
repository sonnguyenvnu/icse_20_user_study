/** 
 * Unsafe version of  {@link #sentinel}. 
 */
public static byte nsentinel(long struct){
  return UNSAFE.getByte(null,struct + AIPropertyStore.SENTINEL);
}
