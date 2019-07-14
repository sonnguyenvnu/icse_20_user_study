/** 
 * Unsafe version of  {@link #dmDefaultSource}. 
 */
public static short ndmDefaultSource(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMDEFAULTSOURCE);
}
