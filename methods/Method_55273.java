/** 
 * Unsafe version of  {@link #dmSpecVersion}. 
 */
public static short ndmSpecVersion(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMSPECVERSION);
}
