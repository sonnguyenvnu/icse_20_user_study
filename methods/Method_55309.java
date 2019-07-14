/** 
 * Unsafe version of  {@link #cb(int) cb}. 
 */
public static void ncb(long struct,int value){
  UNSAFE.putInt(null,struct + DISPLAY_DEVICE.CB,value);
}
