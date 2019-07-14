/** 
 * Unsafe version of  {@link #dmDuplex}. 
 */
public static short ndmDuplex(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMDUPLEX);
}
