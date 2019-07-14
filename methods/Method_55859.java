@NativeType("CUresult") public static int cuCtxSetCurrent(@NativeType("CUcontext") long ctx){
  long __functionAddress=Functions.CtxSetCurrent;
  return callPI(ctx,__functionAddress);
}
