/** 
 * Unsafe version of  {@link #DeviceKey}. 
 */
public static ByteBuffer nDeviceKey(long struct){
  return memByteBuffer(struct + DISPLAY_DEVICE.DEVICEKEY,128 * 2);
}
