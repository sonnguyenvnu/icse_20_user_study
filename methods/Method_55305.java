/** 
 * Unsafe version of  {@link #StateFlags}. 
 */
public static int nStateFlags(long struct){
  return UNSAFE.getInt(null,struct + DISPLAY_DEVICE.STATEFLAGS);
}
