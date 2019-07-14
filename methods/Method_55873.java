@NativeType("CUresult") public static int cuSurfObjectCreate(@NativeType("CUsurfObject *") LongBuffer pSurfObject,@NativeType("CUDA_RESOURCE_DESC const *") CUDA_RESOURCE_DESC pResDesc){
  if (CHECKS) {
    check(pSurfObject,1);
  }
  return ncuSurfObjectCreate(memAddress(pSurfObject),pResDesc.address());
}
