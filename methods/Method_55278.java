/** 
 * Unsafe version of  {@link #dmPaperLength}. 
 */
public static short ndmPaperLength(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMPAPERLENGTH);
}
