/** 
 * Unsafe version of  {@link #dmPrintQuality}. 
 */
public static short ndmPrintQuality(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMPRINTQUALITY);
}
