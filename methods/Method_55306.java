/** 
 * Unsafe version of  {@link #DeviceID}. 
 */
public static ByteBuffer nDeviceID(long struct){
  return memByteBuffer(struct + DISPLAY_DEVICE.DEVICEID,128 * 2);
}
