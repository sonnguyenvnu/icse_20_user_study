/** 
 * Unsafe version of  {@link #DeviceString}. 
 */
public static ByteBuffer nDeviceString(long struct){
  return memByteBuffer(struct + DISPLAY_DEVICE.DEVICESTRING,128 * 2);
}
