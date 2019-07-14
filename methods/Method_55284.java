/** 
 * Unsafe version of  {@link #dmColor}. 
 */
public static short ndmColor(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMCOLOR);
}
