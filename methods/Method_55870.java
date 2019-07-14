@NativeType("CUresult") public static int cuMipmappedArrayCreate(@NativeType("CUmipmappedArray *") PointerBuffer pHandle,@NativeType("CUDA_ARRAY3D_DESCRIPTOR const *") CUDA_ARRAY3D_DESCRIPTOR pMipmappedArrayDesc,@NativeType("unsigned int") int numMipmapLevels){
  if (CHECKS) {
    check(pHandle,1);
  }
  return ncuMipmappedArrayCreate(memAddress(pHandle),pMipmappedArrayDesc.address(),numMipmapLevels);
}
