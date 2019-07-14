/** 
 * Unsafe version of  {@link #dmScale}. 
 */
public static short ndmScale(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMSCALE);
}
