/** 
 * Unsafe version of  {@link #dmTTOption}. 
 */
public static short ndmTTOption(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMTTOPTION);
}
