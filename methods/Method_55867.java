@NativeType("CUresult") public static int cuCtxSetSharedMemConfig(@NativeType("CUsharedconfig") int config){
  long __functionAddress=Functions.CtxSetSharedMemConfig;
  return callI(config,__functionAddress);
}
