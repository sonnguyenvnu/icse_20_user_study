/** 
 * Unsafe version of  {@link #dmDeviceName}. 
 */
public static ByteBuffer ndmDeviceName(long struct){
  return memByteBuffer(struct + DEVMODE.DMDEVICENAME,32 * 2);
}
