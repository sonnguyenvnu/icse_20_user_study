public static int ncuDevicePrimaryCtxGetState(int dev,long flags,long active){
  long __functionAddress=Functions.DevicePrimaryCtxGetState;
  return callPPI(dev,flags,active,__functionAddress);
}
