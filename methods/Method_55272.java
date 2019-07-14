/** 
 * Unsafe version of  {@link #dmDeviceNameString}. 
 */
public static String ndmDeviceNameString(long struct){
  return memUTF16(struct + DEVMODE.DMDEVICENAME);
}
