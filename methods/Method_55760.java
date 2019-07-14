@NativeType("CUresult") public static int cuCtxSetCacheConfig(@NativeType("CUfunc_cache") int config){
  long __functionAddress=Functions.CtxSetCacheConfig;
  return callI(config,__functionAddress);
}
