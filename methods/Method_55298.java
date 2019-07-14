/** 
 * Returns a  {@link ByteBuffer} view of the {@code DeviceString} field. 
 */
@NativeType("TCHAR[128]") public ByteBuffer DeviceString(){
  return nDeviceString(address());
}
