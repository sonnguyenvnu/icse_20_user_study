/** 
 * Decodes the null-terminated string stored in the  {@code DeviceName} field. 
 */
@NativeType("TCHAR[32]") public String DeviceNameString(){
  return nDeviceNameString(address());
}
