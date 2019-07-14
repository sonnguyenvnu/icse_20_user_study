@NativeType("CUresult") public static int cuCtxGetCacheConfig(@NativeType("CUfunc_cache *") IntBuffer pconfig){
  if (CHECKS) {
    check(pconfig,1);
  }
  return ncuCtxGetCacheConfig(memAddress(pconfig));
}
