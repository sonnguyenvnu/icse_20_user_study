@NativeType("CUresult") public static int cuCtxGetLimit(@NativeType("size_t *") PointerBuffer pvalue,@NativeType("CUlimit") int limit){
  if (CHECKS) {
    check(pvalue,1);
  }
  return ncuCtxGetLimit(memAddress(pvalue),limit);
}
