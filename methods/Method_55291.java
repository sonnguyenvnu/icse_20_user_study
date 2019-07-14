/** 
 * Unsafe version of  {@link #dmLogPixels}. 
 */
public static short ndmLogPixels(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMLOGPIXELS);
}
