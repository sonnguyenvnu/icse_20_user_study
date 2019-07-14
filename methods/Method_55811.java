@NativeType("CUresult") public static int cuFuncSetSharedSize(@NativeType("CUfunction") long hfunc,@NativeType("unsigned int") int bytes){
  long __functionAddress=Functions.FuncSetSharedSize;
  if (CHECKS) {
    check(hfunc);
  }
  return callPI(hfunc,bytes,__functionAddress);
}
