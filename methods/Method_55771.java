@NativeType("CUresult") public static int cuMemAllocPitch(@NativeType("CUdeviceptr *") PointerBuffer dptr,@NativeType("size_t *") PointerBuffer pPitch,@NativeType("size_t") long WidthInBytes,@NativeType("size_t") long Height,@NativeType("unsigned int") int ElementSizeBytes){
  if (CHECKS) {
    check(dptr,1);
    check(pPitch,1);
  }
  return ncuMemAllocPitch(memAddress(dptr),memAddress(pPitch),WidthInBytes,Height,ElementSizeBytes);
}
