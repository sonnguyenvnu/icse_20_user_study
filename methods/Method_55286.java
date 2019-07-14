/** 
 * Unsafe version of  {@link #dmYResolution}. 
 */
public static short ndmYResolution(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMYRESOLUTION);
}
