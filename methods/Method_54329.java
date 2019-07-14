/** 
 * Unsafe version of  {@link #transientVbSize}. 
 */
public static int ntransientVbSize(long struct){
  return UNSAFE.getInt(null,struct + BGFXInitLimits.TRANSIENTVBSIZE);
}
