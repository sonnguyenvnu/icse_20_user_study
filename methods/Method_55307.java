/** 
 * Unsafe version of  {@link #DeviceIDString}. 
 */
public static String nDeviceIDString(long struct){
  return memUTF16(struct + DISPLAY_DEVICE.DEVICEID);
}
