/** 
 * Unsafe version of  {@link #DeviceName}. 
 */
public static ByteBuffer nDeviceName(long struct){
  return memByteBuffer(struct + DISPLAY_DEVICE.DEVICENAME,32 * 2);
}
