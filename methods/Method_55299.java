/** 
 * Decodes the null-terminated string stored in the  {@code DeviceString} field. 
 */
@NativeType("TCHAR[128]") public String DeviceStringString(){
  return nDeviceStringString(address());
}
