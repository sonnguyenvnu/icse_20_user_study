@NativeType("CUresult") public static int cuFuncSetSharedMemConfig(@NativeType("CUfunction") long hfunc,@NativeType("CUsharedconfig") int config){
  long __functionAddress=Functions.FuncSetSharedMemConfig;
  if (CHECKS) {
    check(hfunc);
  }
  return callPI(hfunc,config,__functionAddress);
}
