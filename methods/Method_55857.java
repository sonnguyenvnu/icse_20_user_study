@NativeType("CUresult") public static int cuCtxPushCurrent(@NativeType("CUcontext") long ctx){
  long __functionAddress=Functions.CtxPushCurrent;
  if (CHECKS) {
    check(ctx);
  }
  return callPI(ctx,__functionAddress);
}
