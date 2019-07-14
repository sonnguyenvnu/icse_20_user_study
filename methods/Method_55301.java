/** 
 * Decodes the null-terminated string stored in the  {@code DeviceID} field. 
 */
@NativeType("TCHAR[128]") public String DeviceIDString(){
  return nDeviceIDString(address());
}
