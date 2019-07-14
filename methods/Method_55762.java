@NativeType("CUresult") public static int cuCtxAttach(@NativeType("CUcontext *") PointerBuffer pctx,@NativeType("unsigned int") int flags){
  if (CHECKS) {
    check(pctx,1);
  }
  return ncuCtxAttach(memAddress(pctx),flags);
}
