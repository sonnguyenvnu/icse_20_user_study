@NativeType("CUresult") public static int cuDeviceTotalMem(@NativeType("size_t *") PointerBuffer bytes,@NativeType("CUdevice") int dev){
  if (CHECKS) {
    check(bytes,1);
  }
  return ncuDeviceTotalMem(memAddress(bytes),dev);
}
