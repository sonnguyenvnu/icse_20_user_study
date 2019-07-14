/** 
 * Unsafe version of  {@link #cb}. 
 */
public static int ncb(long struct){
  return UNSAFE.getInt(null,struct + DISPLAY_DEVICE.CB);
}
