/** 
 * Unsafe version of  {@link #transientIbSize}. 
 */
public static int ntransientIbSize(long struct){
  return UNSAFE.getInt(null,struct + BGFXInitLimits.TRANSIENTIBSIZE);
}
