/** 
 * Unsafe version of  {@link #dmCollate}. 
 */
public static short ndmCollate(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMCOLLATE);
}
