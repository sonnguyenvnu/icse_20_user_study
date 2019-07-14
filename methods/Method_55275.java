/** 
 * Unsafe version of  {@link #dmDriverExtra}. 
 */
public static short ndmDriverExtra(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMDRIVEREXTRA);
}
