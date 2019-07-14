@NativeType("CUresult") public static int cuMemHostGetDevicePointer(@NativeType("CUdeviceptr *") PointerBuffer pdptr,@NativeType("void *") ByteBuffer p,@NativeType("unsigned int") int Flags){
  if (CHECKS) {
    check(pdptr,1);
  }
  return ncuMemHostGetDevicePointer(memAddress(pdptr),memAddress(p),Flags);
}
