public static int ncuDeviceGetLuid(long luid,long deviceNodeMask,int dev){
  long __functionAddress=Functions.DeviceGetLuid;
  if (CHECKS) {
    check(__functionAddress);
  }
  return callPPI(luid,deviceNodeMask,dev,__functionAddress);
}
