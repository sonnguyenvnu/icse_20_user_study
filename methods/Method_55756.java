@NativeType("CUresult") public static int cuCtxSetLimit(@NativeType("CUlimit") int limit,@NativeType("size_t") long value){
  long __functionAddress=Functions.CtxSetLimit;
  return callPI(limit,value,__functionAddress);
}
