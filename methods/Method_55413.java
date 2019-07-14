/** 
 * Unsafe version of:  {@link #EnumDisplayDevices} 
 */
public static int nEnumDisplayDevices(long lpDevice,int iDevNum,long lpDisplayDevice,int dwFlags){
  long __functionAddress=Functions.EnumDisplayDevices;
  return callPPI(lpDevice,iDevNum,lpDisplayDevice,dwFlags,__functionAddress);
}
