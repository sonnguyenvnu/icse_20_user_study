@NativeType("CUresult") public static int cuCtxDetach(@NativeType("CUcontext") long ctx){
  long __functionAddress=Functions.CtxDetach;
  if (CHECKS) {
    check(ctx);
  }
  return callPI(ctx,__functionAddress);
}
