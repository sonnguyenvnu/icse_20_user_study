/** 
 * Unsafe version of  {@link #dmPaperSize}. 
 */
public static short ndmPaperSize(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMPAPERSIZE);
}
