/** 
 * Unsafe version of  {@link #dmOrientation}. 
 */
public static short ndmOrientation(long struct){
  return UNSAFE.getShort(null,struct + DEVMODE.DMORIENTATION);
}
