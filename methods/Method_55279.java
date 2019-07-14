/** 
 * Unsafe version of  {@link #dmPaperWidth}. 
 */
public static short ndmPaperWidth(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMPAPERWIDTH);
}
