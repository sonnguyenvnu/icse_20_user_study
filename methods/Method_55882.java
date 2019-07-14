public static int ncuDevicePrimaryCtxRetain(long pctx,int dev){
  long __functionAddress=Functions.DevicePrimaryCtxRetain;
  return callPI(pctx,dev,__functionAddress);
}
